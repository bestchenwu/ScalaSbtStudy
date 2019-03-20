package FunctionProgramming.state

import FunctionProgramming.state.State.RAND


/**
  * 随机数生成器
  *
  * @author chenwu on 2019.3.6
  */
trait RNG {

  def nextInt: (Int, RNG)
}

object RNG {

  case class SimpleRng(seed: Long) extends RNG {

    /**
      * 返回一个随机数(并把下一次随机的状态给调用方)
      *
      * @return (Int,RNG)
      * @author chenwu on 2019.3.6
      */
    override def nextInt: (Int, RNG) = {
      val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
      val newRng = SimpleRng(newSeed)
      val n = (newSeed >>> 16).toInt
      (n, newRng)
    }
  }

  /**
    * 返回一个随机数pair
    *
    * @param rng
    * @return (随机数pair,RNG)
    * @author chenwu on 2019.3.6
    */
  def randomPair(rng: RNG): ((Int, Int), RNG) = {
    val (int1, rng1) = rng.nextInt
    val (int2, rng2) = rng1.nextInt
    ((int1, int2), rng2)
  }

  /**
    * 返回0到Int.maxValue之间的数字<br/>
    * 由于Int.minValue比-Int.maxValue小1
    *
    * @param rng
    * @return
    * @author chenwu on 2019.3.6
    */
  def nonNegativeInt(rng: RNG): (Int, RNG) = {
    val (int1, rng2) = rng.nextInt
    (if (int1 < 0) -(int1 + 1) else int1, rng2)
  }

  /**
    * 获取0-1之间的double数
    *
    * @param rng
    * @return (Double,RNG)
    * @author chenwu on 2019.3.6
    */
  def randomDouble(rng: RNG): (Double, RNG) = {
    val (int, rng2) = rng.nextInt
    (int.toDouble / Int.MaxValue, rng2)
  }

  /** 一个状态到另一个状态,称为状态转移 **/
  //接受RNG类型的参数,返回(A,RNG)的组合
  //scala中type用于声明一个类型别名,这里把RNG => (A, RNG)这样的转换称为一个组合子
  type RAND[+A] = RNG => (A, RNG) //组合子

  val rand_int: RAND[Int] = _.nextInt

  def unit[A](a: A): RAND[A] = rng => (a, rng)

  def map[A, B](s: RAND[A])(f: A => B): RAND[B] = rng => {
    val (a, rng2) = s(rng)
    val newB = f(a)
    (newB, rng2)
  }

  def randomDoubleViaRand(rng: RAND[Int]): RAND[Double] = {
    map(rng)(x => x.toDouble / Int.MaxValue)
  }

  def map2[A, B, C](ra: RAND[A], rb: RAND[B])(f: (A, B) => C): RAND[C] = rng => {
    val (a, rngA) = ra(rng)
    val (b, rngB) = rb(rngA)
    (f(a, b), rngB)
  }

  def flatMap[A, B](f: RAND[A])(g: A => RAND[B]): RAND[B] = rng => {
    val (a, rngA) = f(rng)
    val randB = g(a)
    randB(rngA)
  }

  def mapViaFlatMap[A, B](s: RAND[A])(f: A => B): RAND[B] = {
    flatMap(s)(a => unit(f(a)))
  }

  def sequence[A](fs: List[RAND[A]]): RAND[List[A]] =
    fs.foldRight(unit(List[A]()))((f, acc) => map2(f, acc)(_ :: _))

  def _ints(count: Int): RAND[List[Int]] =
    sequence(List.fill(count)(rand_int))

  def map2ViaFlatMap[A, B, C](ra: RAND[A], rb: RAND[B])(f: (A, B) => C): RAND[C] = {
    flatMap(ra)(a => map(rb)(b => f(a, b)))
  }

  def boolean(rng: RNG): (Boolean, RNG) =
    rng.nextInt match { case (i,rng2) => (i%2==0,rng2) }

}

case class State[S, +A](run: S => (A, S)) {

  def map[B](f: A => B): State[S, B] = {
      flatMap(a=>unit(f(a)))
  }

  def flatMap[B](f: A => State[S, B]): State[S, B] = State(s => {
    val (a, s1) = run(s)
    f(a).run(s1)
  })

  def get[S]: State[S, S] = State(s => (s, s))

  def set[S](s: S): State[S, Unit] = State(_ => ((), s))

  def modify[S](f: S => S): State[S, Unit] = for {
    s <- get
    _ <- set(f(s))

  } yield ()

  def unit[S, A](a: A): State[S, A] =
    State(s => (a, s))


}

object State {

  type RAND[A] = State[RNG, A]

  def sequence[S, A](sas: List[State[S, A]]): State[S, List[A]] = {
    def go(s: S, actions: List[State[S,A]], acc: List[A]): (List[A],S) =
      actions match {
        case Nil => (acc.reverse,s)
        case h :: t => h.run(s) match { case (a,s2) => go(s2, t, a :: acc) }
      }
    State((s: S) => go(s,sas,List()))
  }


}
