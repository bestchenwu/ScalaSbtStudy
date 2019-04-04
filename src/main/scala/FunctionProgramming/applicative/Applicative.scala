package FunctionProgramming.applicative

import FunctionProgramming.Monald.Functor

trait Applicative[F[_]] extends Functor[F] {

  def map2[A, B, C](fa: F[A], fb: F[B])(f: (A, B) => C): F[C]

  def unit[A](a: => A): F[A]

  def map[A, B](fa: F[A])(f: A => B): F[B] = map2(fa, unit(()))((a, _) => f(a))

  def traverse[A, B](lma: List[A])(f: A => F[B]): F[List[B]] = lma.foldRight(unit(List[B]()))((a, bList) => map2(f(a), bList)(_ :: _))

  def sequence[A](lma: List[F[A]]): F[List[A]] = traverse(lma)((x: F[A]) => x)

  def replicateM[A](n: Int, fa: F[A]): F[List[A]] = sequence(List.fill(n)(fa))

  def product[A, B](fa: F[A], fb: F[B]): F[(A, B)] = map2(fa, fb)((_, _))

  def map3[A, B, C, D](fa: F[A], fb: F[B], fc: F[C])(f: (A, B, C) => D): F[D] = map2(map2(fa, fb)((a, b) => (a, b)), fc)((ab, c) => f(ab._1, ab._2, c))

  def map4[A, B, C, D, E](fa: F[A], fb: F[B], fc: F[C], fd: F[D])(f: (A, B, C, D) => E):F[E] = map2(map2(fa, fb)((a, b) => (a, b)), map2(fc, fd)((c, d) => (c, d)))((ab, cd) => f(ab._1, ab._2, cd._1, cd._2))
}
