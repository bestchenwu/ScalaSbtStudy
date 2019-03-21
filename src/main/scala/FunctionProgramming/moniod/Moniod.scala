package FunctionProgramming.moniod

import FunctionProgramming.state.RNG.SimpleRng
import FunctionProgramming.test.{Gen, Prop}

/**
  * moniod描述了结合律和同一律
  * 结合律是op(op(x,y),z) == op(op(x,z),y)
  * 同一律是op(x,zero) == x和op(zero,x)==x
  *
  * @tparam A
  */
trait Moniod[A] {
  def op(a1: A, a2: A): A

  def zero: A

}

object Moniod {

  val StringMoniod = new Moniod[String] {
    override def op(a1: String, a2: String): String = a1 + a2

    override def zero: String = ""
  }

  def ListMoniod[A] = new Moniod[List[A]] {
    override def op(a1: List[A], a2: List[A]): List[A] = a1 ++ a2

    override def zero: List[A] = Nil
  }

  val intAddition: Moniod[Int] = new Moniod[Int] {
    override def op(a1: Int, a2: Int): Int = a1 + a2

    override def zero: Int = 0
  }

  val intMultiplication: Moniod[Int] = new Moniod[Int] {
    override def op(a1: Int, a2: Int): Int = a1 * a2

    override def zero: Int = 0
  }

  val booleanOr: Moniod[Boolean] = new Moniod[Boolean] {
    override def op(a1: Boolean, a2: Boolean): Boolean = a1 || a2

    override def zero: Boolean = false
  }

  val booleanAnd: Moniod[Boolean] = new Moniod[Boolean] {
    override def op(a1: Boolean, a2: Boolean): Boolean = a1 && a2

    override def zero: Boolean = false
  }

  def optionMoniod[A]: Moniod[Option[A]] = new Moniod[Option[A]] {
    override def op(a1: Option[A], a2: Option[A]): Option[A] = if (a1.isEmpty) Some(a2.get) else Some(a1.get)

    override def zero: Option[A] = None
  }

  /**
    * 参数和出参都是相同类型的函数为自函数(endoFunction)<br/>
    * 所以这里的类型是一个函数,接受A返回A
    *
    * @param a
    * @tparam A
    * @return
    */
  def endoMoniod[A]: Moniod[A => A] = new Moniod[A => A] {
    /** a1 compose a2表示 先调用a2 后调用a1 **/
    override def op(a1: A => A, a2: A => A): A => A = a1 compose a2

    override def zero: A => A = (a: A) => a
  }

  def monoidLaws[A](m: Moniod[A], gen: Gen[A]): Prop = new Prop {
    override def check: Boolean = false

  }

  def concetenate[A](as: List[A], m: Moniod[A]): A = as.foldLeft(m.zero)(m.op)

  def foldMap[A, B](as: List[A], m: Moniod[B])(f: A => B): B = {
    as.map(f).foldLeft(m.zero)(m.op)
  }

  /**
    * 并行计算
    *
    * @param v
    * @param m
    * @param f
    * @tparam A
    * @tparam B
    * @return
    */
  def foldMapV[A, B](v: IndexedSeq[A], m: Moniod[B])(f: A => B): B = {
    if (v.size == 2) {
      m.op(f(v(0)), f(v(1)))
    } else if (v.size == 1) {
      m.op(f(v(0)), m.zero)
    } else {
      val (leftSeq, rightSeq) = v.splitAt(v.size / 2)
      m.op(foldMapV(leftSeq, m)(f), foldMapV(rightSeq, m)(f))
    }
  }
}


