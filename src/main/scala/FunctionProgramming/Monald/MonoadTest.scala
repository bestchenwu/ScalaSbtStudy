package FunctionProgramming.Monald

import scala.util.Try

object MonoadTest {

  def main(args: Array[String]): Unit = {
    //    val list = List(1, 2, 3)
    //    val result = Monoad.ListMonad.flatMap(list)((x: Int) => List(x, x * 2))
    //    //输出List(1,2,3,4,5,6)
    //    println(result)
    //    val list1 = List(Some(1),Some(2),Some(3))
    //    //val result2 = Monoad.ListMonad.sequence(list1)
    //    val result2 = Monoad.OptionMonad.sequence(list1)
    //输出Some(List(1,2,3))
    //println(result2)
    //    val result3 = Monoad.OptionMonad.traverse(List("1", "a", "3"))((x: String) => try {
    //      Some(x.toInt)
    //    } catch {
    //      case e:Exception => Some(e.getMessage)
    //    })
    //    print(result3)
    val list = Monoad.OptionMonad.replicateM(3, Some(3))
    print(list)
  }
}
