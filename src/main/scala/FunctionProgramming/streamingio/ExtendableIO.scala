package FunctionProgramming.streamingio

import FunctionProgramming.streamingio.ExtendableIO.{Emit, ExtendableEmit, ExtendableHalt}

import scala.util.Try

trait ExtendableIO[F[_], O] {

  def onHalt[F[_], O](f: Throwable => ExtendableIO[F, O]): ExtendableIO[F, O] = this match {
    case ExtendableHalt(e) => f(e)
    case ExtendableEmit(h,t)=>ExtendableEmit(h,t.onHalt(f))
      
  }
}

object ExtendableIO {

  case class ExtendableAwait[F[_], A, O](req: F[A], recv: Either[Throwable, A] => ExtendableIO[F, O]) extends ExtendableIO[F, O]

  case class ExtendableEmit[F[_], O](head: O, tail: ExtendableIO[F, O]) extends ExtendableIO[F, O]

  case class ExtendableHalt[F[_], O](err: Throwable) extends ExtendableIO[F, O]

  case object End extends Exception

  case object Kill extends Exception


}
