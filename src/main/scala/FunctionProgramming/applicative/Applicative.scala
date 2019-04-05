package FunctionProgramming.applicative

import FunctionProgramming.Monald.Functor
import FunctionProgramming.moniod.Foldable

trait Applicative[F[_]] extends Functor[F] {

  def map2[A, B, C](fa: F[A], fb: F[B])(f: (A, B) => C): F[C]

  def unit[A](a: => A): F[A]

  def map[A, B](fa: F[A])(f: A => B): F[B] = map2(fa, unit(()))((a, _) => f(a))

  def traverse[A, B](lma: List[A])(f: A => F[B]): F[List[B]] = lma.foldRight(unit(List[B]()))((a, bList) => map2(f(a), bList)(_ :: _))

  def sequence[A](lma: List[F[A]]): F[List[A]] = traverse(lma)((x: F[A]) => x)

  /**
    * map与map相加,可以使用map.updated(k,v)或者map+(k-<v)
    *
    * @param ofa
    * @tparam K
    * @tparam V
    * @return
    */
  def sequenceMap[K, V](ofa: Map[K, F[V]]): F[Map[K, V]] = ofa.foldRight(unit(Map[K, V]()))((kvTuple, bMap) => map2(kvTuple._2, bMap)((v, bMap) => bMap.updated(kvTuple._1, v)))

  def replicateM[A](n: Int, fa: F[A]): F[List[A]] = sequence(List.fill(n)(fa))

  def product[A, B](fa: F[A], fb: F[B]): F[(A, B)] = map2(fa, fb)((_, _))

  def map3[A, B, C, D](fa: F[A], fb: F[B], fc: F[C])(f: (A, B, C) => D): F[D] = map2(map2(fa, fb)((a, b) => (a, b)), fc)((ab, c) => f(ab._1, ab._2, c))

  def map4[A, B, C, D, E](fa: F[A], fb: F[B], fc: F[C], fd: F[D])(f: (A, B, C, D) => E): F[E] = map2(map2(fa, fb)((a, b) => (a, b)), map2(fc, fd)((c, d) => (c, d)))((ab, cd) => f(ab._1, ab._2, cd._1, cd._2))



}
//trait Traverse[F[_]] extends Functor[F] with Foldable[F] {
//  self =>
//  def traverse[M[_] : Applicative, A, B](fa: F[A])(f: A => M[B]): M[F[B]] =
//    sequence(map(fa)(f))
//
//  def sequence[M[_] : Applicative, A](fma: F[M[A]]): M[F[A]] =
//    traverse(fma)(ma => ma)
//
//  type Id[A] = A
//
//  val idMonad = new Monad[Id] {
//    def unit[A](a: => A) = a
//    override def flatMap[A,B](a: A)(f: A => B): B = f(a)
//  }
//
//  def map[A,B](fa: F[A])(f: A => B): F[B] =
//    traverse[Id, A, B](fa)(f)(idMonad)
//}

