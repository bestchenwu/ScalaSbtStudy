package FunctionProgramming.Monald

trait Function[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]

  def distribute[A, B](fab: F[(A, B)]): (F[A], F[B]) = (map(fab)(_._1), map(fab)(_._2))

  def codistribute[A, B](e: Either[F[A], F[B]]): F[Either[A, B]] = e match {
    case Left(fa) => map(fa)(Left(_))
    case Right(fb) => map(fb)(Right(_))
  }
}

class Monoad {

  val listFunctor = new Function[List] {
    override def map[A, B](fa: List[A])(f: A => B): List[B] = fa map f
  }

}

object Monoad {

}
