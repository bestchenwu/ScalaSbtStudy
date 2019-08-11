package diveIntoScala.unit8_scala_collection

import scala.collection.mutable.ArrayBuffer

object IteratorTest {

  def main(args: Array[String]): Unit = {
      val a = ArrayBuffer("a","b","c")
      val i = a.iterator
      a.remove(0,3)
      println("has next:"+i.hasNext)
      //这里抛出IndexedOutOfBoundException
      //Iterable适合需要外部迭代器,但不能随机访问的场景
      println(i.next())
      //->是来自于scala.predef里面的隐式转换,将a,1 转换为Tuple2 二元数组
      val map = Map("a"->1)
  }
}
