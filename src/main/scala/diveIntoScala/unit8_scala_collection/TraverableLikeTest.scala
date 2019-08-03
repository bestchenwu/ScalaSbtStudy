package diveIntoScala.unit8_scala_collection

object TraverableLikeTest {

  def main(args: Array[String]): Unit = {
      val list = List(1,2,3,4).toIterator
      //println(list.drop(2))
      val (iter1,iter2) = list.partition(_%2==0)
      while(iter1.hasNext){
        println(iter1.next())
      }
      val set = Set(12,3,4,5,6,7)
  }
}
