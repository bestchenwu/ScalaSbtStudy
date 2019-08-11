package diveIntoScala.unit8_scala_collection


object FileTravserableTest {
  def main(args: Array[String]): Unit = {
    val fileTravserable = new FileTravserable("/data/problem/problem.txt")
//    val x = for {
//      line <- fileTravserable
//
//    } yield line
//    //这里输出的x是一个List
//    println(x)
      //这时候没有输出finish iterator file
      //因为Travserable在Breakable里面循环的时候优雅的抛出BreakControl->scala.util.control.ControlThrowable
      //这是一种预分配的异常,JVM可以高效的抛出和捕获
      val x = fileTravserable.take(2)
      println(x)
  }
}
