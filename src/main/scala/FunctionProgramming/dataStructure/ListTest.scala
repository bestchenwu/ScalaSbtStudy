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
    val list1 = List("a", "b", "c","19","20")
    //取List的尾部
    //    val tailList = List.tail(list)
    //    //打印list
    //    val result = List.printList(tailList)
    //    println(result)
    //    //置换list的头部元素
    //    val newList = List.setHead(list, "abc")
    //    val result1 = List.printList(newList)
    //println(result1)
    //去除头几个元素
    //    val dropList = List.drop(list, 2)
    //    val result = List.printList(dropList)
    //    println(result)
    var newList = List.appendList(list, list1)
    var result1 = List.printList(newList)
    println(result1)

    def transfer[A](x:A)={
      try{
        Integer.parseInt(x.toString)
        true
      }catch{
        case _ => false
      }
    }

    newList =List.dropWhile(newList,transfer)
    result1 = List.printList(newList)
    println(result1)
  }
}
