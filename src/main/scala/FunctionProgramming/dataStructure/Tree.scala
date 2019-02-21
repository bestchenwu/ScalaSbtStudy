package FunctionProgramming.dataStructure

sealed trait Tree[+A]

case class Leaf[A](value: A) extends Tree[A]

case class Branch[A](left: Tree[A], right: Tree[A], node: A) extends Tree[A]

object Tree {


  /**
    * 计算树的节点数(包括分支节点和叶子节点)
    *
    * @param tree
    * @tparam A
    * @return chenwu on 2019.2.21
    */
  def size[A](tree: Tree[A]): Int = {
    tree match {
      case Leaf(_) => 1
      case Branch(left, right, _) => 1 + size(left) + size(right)
      case null => 0
    }
  }

  /**
    * 计算树中值最大的节点
    *
    * @param tree
    * @return Int
    * @author chenwu on 2019.2.21
    */
  def maximum(tree: Tree[Int]): Int = {
    def maxValue: Int = 0

    def loop(tree: Tree[Int], maxValue: Int): Int = {
      tree match {
        case Leaf(value) => value.max(maxValue)
        case Branch(left, right, node) => loop(left, maxValue) max loop(right, maxValue) max node.max(maxValue)
        case null => 0
      }
    }

    loop(tree, maxValue)
  }

  /**
    *
    * 构建二叉树(比当前节点小的放左边,比当前节点大的放右边)
    *
    * @param a
    * @tparam A
    * @return Tree
    */
  //TODO:
  //  def apply[A](a: A*): Tree[A] = {
  //      null
  //  }
}
