package unitNighteen

import unitNighteen.LinkedListTest.list

import scala.util.Random

class LinkedList[A] {

  private class Node[A](var data: A) {
    //var data:String = _
    var nextNode: Node[A] = _

    override def toString: String = {
      "Node:" + data
    }
  }

  private var root: Node[A] = _

  def addNode(data: A): Unit = {
    val newNode = new Node[A](data)
    newNode.nextNode = root
    root = newNode
  }

  def printList(): Unit = {
    while (root != null) {
      println(root.data);
      root = root.nextNode
    }
  }

  def randomElement[A](seq: Seq[A]): Unit = {
    val index = Random.nextInt(seq.length)
    println(s"index=$index")
    seq(index)
  }
}

object LinkedListTest extends App {
  val list = new LinkedList[String]();
//  list.addNode("test")
//  list.addNode("test1")
//  list.printList()
  println(list.randomElement(List(1,2,3)).getClass)
  println(list.randomElement(List(1,2,3)))
    //list.randomElement(Seq("a","b","c")).
  //println(list.randomElement(List(1,2,3)))
  //println(list.randomElement(Seq("a","b","c")))
}
