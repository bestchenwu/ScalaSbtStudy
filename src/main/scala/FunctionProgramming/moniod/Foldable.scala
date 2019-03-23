package FunctionProgramming.moniod

/**
  * 这里的F表示类型构造器,接受一个类型参数<br/>
  * 所以Foldable称为高阶类型构造函数
  *
  * @tparam F
  */
trait Foldable[F[_]] {

  def foldRight[A, B](as: F[A])(Z: B)(f: (A, B) => B): B

  def foldLeft[A, B](as: F[A])(Z: B)(f: (B, A) => B): B

  def foldMap[A, B](as: F[A])(f: A => B)(mb: Moniod[B]): B

  def concatenate[A](as: F[A])(m: Moniod[A]): A = foldLeft(as)(m.zero)(m.op)
}

