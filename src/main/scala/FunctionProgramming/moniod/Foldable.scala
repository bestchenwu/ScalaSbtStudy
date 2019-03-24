package FunctionProgramming.moniod

import FunctionProgramming.dataStructure.{Branch, Leaf, Tree}

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

object Foldable {

  val foldableList: Foldable[List] = new Foldable[List] {
    override def foldRight[A, B](as: List[A])(Z: B)(f: (A, B) => B): B = {
      as.foldRight(Z)(f)
    }

    override def foldLeft[A, B](as: List[A])(Z: B)(f: (B, A) => B): B = {
      as.foldLeft(Z)(f)
    }

    override def foldMap[A, B](as: List[A])(f: A => B)(mb: Moniod[B]): B = {
      Moniod.foldMap(as, mb)(f)
    }
  }

  val foldTree: Foldable[Tree] = new Foldable[Tree] {
    override def foldRight[A, B](as: Tree[A])(Z: B)(f: (A, B) => B): B = {
      as match {
        case Leaf(value) => f(value, Z)
        case Branch(left, right, nodeA) => foldRight(right)(foldRight(left)(f(nodeA, Z))(f))(f)
      }
    }


    override def foldLeft[A, B](as: Tree[A])(Z: B)(f: (B, A) => B): B = {
      as match {
        case Leaf(value) => f(Z, value)
        case Branch(left, right, nodeA) => foldLeft(left)(foldLeft(right)(f(Z, nodeA))(f))(f)
      }
    }

    override def foldMap[A, B](as: Tree[A])(f: A => B)(mb: Moniod[B]): B = {
      as match {
        case Leaf(value) => f(value)
        case Branch(left, right, nodeA) => mb.op(foldMap(left)(f)(mb), foldMap(right)(f)(mb))
      }
    }
  }
}



