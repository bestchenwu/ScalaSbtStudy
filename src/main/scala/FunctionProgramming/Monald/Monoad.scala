package FunctionProgramming.Monald

//import org.scalacheck.Gen

import java.util.concurrent.Executors

import FunctionProgramming.parallelism.Par.Par
import FunctionProgramming.parallelism.{Par}
import FunctionProgramming.test.Gen
import FunctionProgramming.laziness.Stream


trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]

  def distribute[A, B](fab: F[(A, B)]): (F[A], F[B]) = (map(fab)(_._1), map(fab)(_._2))

  def codistribute[A, B](e: Either[F[A], F[B]]): F[Either[A, B]] = e match {
    case Left(fa) => map(fa)(Left(_))
    case Right(fb) => map(fb)(Right(_))
  }

  def map2[A, B, C](fa: Gen[A], fb: Gen[B])(f: (A, B) => C): Gen[C] = fa flatMap ((a => fb.map(b => f(a, b))))

  //def map2[A,B,C](fa:Parser[A],fb:Parser[B])(f:(A,B)=>C):Parser[C]

  def map2[A, B, C](fa: Option[A], fb: Option[B])(f: (A, B) => C): Option[C] = fa.flatMap(a => fb.map(b => f(a, b)))
}

trait Monoad[F[_]] extends Functor[F] {

  def unit[A](a: => A): F[A]

  def flatMap[A, B](ma: F[A])(f: A => F[B]): F[B]

  def map[A, B](fa: F[A])(f: A => B): F[B] = flatMap(fa)(a => unit(f(a)))

  def map2[A, B, C](ma: F[A], mb: F[B])(f: (A, B) => C): F[C] = flatMap(ma)(a => map(mb)(b => f(a, b)))

  val listFunctor = new Functor[List] {
    override def map[A, B](fa: List[A])(f: A => B): List[B] = fa map f
  }

  //def sequence[A](lma:List[F[A]]):F[List[A]] = unit(List[A]())

  def sequence[A](lma: List[F[A]]): F[List[A]] = lma.foldRight(unit(List[A]()))((lmb, a) => map2(lmb, a)(_ :: _))

  def traverse[A, B](lma: List[A])(f: A => F[B]): F[List[B]] = sequence(lma.map(f))

  def replicateM[A](n: Int, ma: F[A]): F[List[A]] = sequence(List.fill(n)(ma))

  def compose[A, B, C](f: A => F[B], g: B => F[C]): A => F[C] = (a: A) => flatMap(f(a))(g)
}

object Monoad {
  val genMonad = new Monoad[Gen] {
    override def unit[A](a: => A): Gen[A] = Gen.unit(a)

    override def flatMap[A, B](ma: Gen[A])(f: A => Gen[B]): Gen[B] = ma flatMap (f)
  }

  val ParMonad = new Monoad[Par] {

    val es = Executors.newFixedThreadPool(1)

    override def unit[A](a: => A): Par[A] = Par.unit(a)

    override def flatMap[A, B](ma: Par[A])(f: A => Par[B]): Par[B] = Par.flatMapViaJoin(ma)(f)
  }

  val OptionMonad = new Monoad[Option] {
    override def unit[A](a: => A): Option[A] = Some(a)

    override def flatMap[A, B](ma: Option[A])(f: A => Option[B]): Option[B] = ma.flatMap(f)
  }

  val StreamMonad = new Monoad[Stream] {
    override def unit[A](a: => A): Stream[A] = Stream(a)

    override def flatMap[A, B](ma: Stream[A])(f: A => Stream[B]): Stream[B] = ma.flatMap(f)
  }

  val ListMonad = new Monoad[List] {
    override def unit[A](a: => A): List[A] = List(a)

    override def flatMap[A, B](ma: List[A])(f: A => List[B]): List[B] = ma.flatMap(f)
  }


}
