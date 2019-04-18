package FunctionProgramming.streamingio

import scala.util.Try

trait ExtendableIO[F[_], O] {

}

object ExtendableIO {

  case class Await[F[_], A, O](req: F[A], recv: Either[Throwable, A] => ExtendableIO[F, O]) extends ExtendableIO[F, O]

  case class Emit[F[_], O](head: O, tail: ExtendableIO[F, O]) extends ExtendableIO[F, O]

  case class Halt[F[_], O](err: Throwable) extends ExtendableIO[F, O]

  case object End extends Exception

  case object Kill extends Exception

//  def onHalt[F[_], O](f: Throwable => ExtendableIO[F, O]): ExtendableIO[F, O] = this match {
//    //case Halt(e) => Try(f(e))
//    case Emit(h,t)=>Emit(h,t.onHalt(f))
//  }
}
