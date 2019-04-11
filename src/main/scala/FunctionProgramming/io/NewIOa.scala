package FunctionProgramming.io

import FunctionProgramming.Monald.Monoad

object NewIOa{
  sealed trait NewIO[A] {
    def flatMap[B](f: A => NewIO[B]): NewIO[B] = FlatMap(this, f)

    def map[B](f: A => B): NewIO[B] = flatMap(f andThen (Return(_)))

  }

  object NewIO extends Monoad[NewIO] { // Notice that none of these operations DO anything
    def unit[A](a: => A): NewIO[A] = Return(a)
    def flatMap[A,B](a: NewIO[A])(f: A => NewIO[B]): NewIO[B] = a flatMap f
    def suspend[A](a: => NewIO[A]) =
      Suspend(() => ()).flatMap { _ => a }

  }

//  def run[A](io: NewIO[A]): A = io match {
//    case Return(a) => a
//    case Suspend(r) => r()
//    case FlatMap(x, f) => x match {
//      case Return(a) => run(f(a))
//      case Suspend(r) => run(f(r()))
//      case FlatMap(y, g) => run(y flatMap (a => g(a) flatMap f))
//    }
//  }

  /**
    * 没有其他步骤,并立即返回A的纯计算
    *
    * @param a
    * @tparam A
    */
  case class Return[A](a: A) extends NewIO[A]

  /**
    * 计算暂停,这里resume不接受任何参数并作用产生结果
    *
    * @param resume
    * @tparam A
    */
  case class Suspend[A](resume: () => A) extends NewIO[A]

  /**
    * 两个步骤的组合,首先会处理子计算sub并当其返回继续计算k
    *
    * @param sub
    * @param k
    * @tparam A
    * @tparam B
    */
  case class FlatMap[A, B](sub: NewIO[A], k: A => NewIO[B]) extends NewIO[B]
}






