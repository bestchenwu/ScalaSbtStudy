package FunctionProgramming.test

import FunctionProgramming.state.{RNG, State}

case class Gen[+A](sample: State[RNG, A]) {
  val boolean: Gen[Boolean] =
    Gen(State(RNG.boolean))

  //val double:Gen[Double]=Gen(State(rng=>RNG.randomDouble(rng)))

  def map[B](f :A=>B):Gen[B] = {
    Gen(sample.map(f))
  }

  def flatMap[B](f: A => Gen[B]): Gen[B] = {
    Gen(sample.flatMap(a => f(a).sample))
  }

  def listOfN(size: Gen[Int]): Gen[List[A]] = {
    size.flatMap(n => Prop.listOfN(n, this))
  }

  def union[A](g1: Gen[A], g2: Gen[A]): Gen[A] = {
    boolean.flatMap(b => if (b) g1 else g2)
  }

  def weighted[A](g1: (Gen[A], Double), g2: (Gen[A], Double)): Gen[A] = {
      if(g1._2>g2._2) g1._1 else g2._1
  }

  def listOfN(size: Int): Gen[List[A]] =
    Gen.listOfN(size, this)

}
object  Gen{

  val uniform: Gen[Double] = Gen(State(RNG.rand_double))

  def unit[A](a: => A): Gen[A] =
    Gen(State.unit(a))

  def choose(start: Int, stopExclusive: Int): Gen[Int] =
    Gen(State(RNG.nonNegativeInt).map(n => start + n % (stopExclusive-start)))

  def listOfN[A](n: Int, g: Gen[A]): Gen[List[A]] =
    Gen(State.sequence(List.fill(n)(g.sample)))

  def stringN(n:Int):Gen[String] = listOfN(n, choose(0,127)).map(_.map(_.toChar).mkString)



}

