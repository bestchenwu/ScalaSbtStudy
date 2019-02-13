package FunctionProgramming.dataStructure

//导入时候做别名

object ListTest {

  def echo[A](strings: A*) = {
    for (item <- strings) {
      print(item)
    }
  }

  def main(args: Array[String]): Unit = {
    val list = List("13", "15", "18")
    //取List的尾部
    val tailList = List.tail(list)
    //打印list
    val result = List.printList(tailList)
    println(result)
    //置换list的头部元素
    val newList = List.setHead(list, "abc")
    val result1 = List.printList(newList)
    println(result1)
  }
}
