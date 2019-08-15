package diveIntoScala.unit8_scala_collection

object ObservableTest {

  def main(args: Array[String]): Unit = {
      //view 延迟计算
     val list =  List(1,2,3).view.map(_+1)
      println(list)
    val list2 = List(1,2,3).par.map(_+1)
    println(list2)
  }
}
