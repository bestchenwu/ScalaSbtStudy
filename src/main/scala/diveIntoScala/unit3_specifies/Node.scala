package diveIntoScala.unit3_specifies

import scala.annotation.tailrec

case class Node(name: String, edges: List[Node] = Nil) {

}

object Node {

  def searchWidthFirstLy(start: Node, p: Node => Boolean) = {
    @tailrec
    def loop(nodes: List[Node], visted: Set[Node]): Option[Node] = {
      nodes match {
        case head :: _ if (p(head)) => Some(head)
        //一个加号表示ist与元素的附加,两个加号表示list与list的相加
        case head :: tail if (!visted.contains(head)) => loop(tail ++ head.edges, visted + head)
        case _ :: tail => loop(tail, visted)
        case Nil => None
      }


    }

    loop(List(start), Set())
  }

}
