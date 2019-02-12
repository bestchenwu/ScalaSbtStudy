package FunctionProgramming.dataStructure

//导入时候做别名
import FunctionProgramming.dataStructure.List

object ListTest {

  def echo[A](strings: A*) = {
    for (item <- strings) {
      print(item)
    }
  }

  def main(args: Array[String]): Unit = {
        val list = List("13", "15","18")
        val tailList = List.tail(list)
        val result = List.printList(tailList)
        println(result)
    //    val sumResult = MyList.sum(list)
    //    print(sumResult)
//    val x = MyList(1, 2, 3, 4, 5) match {
//      case Cons(x, Cons(2, Cons(4, _))) => x
//      case Nil => 42
//      case Cons(x,Cons(y,Cons(3,Cons(4,_)))) => x+y
//      case Cons(h,t) => h+MyList.sum(t)
//    }
//    print(x)
    //        val array  = Array(1,2,3)
    //        echo(array: _*)

  }
}
