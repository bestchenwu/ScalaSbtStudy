package FunctionProgramming.streamingio

import FunctionProgramming.streamingio.Process

object ProcessTest {
  def main(args: Array[String]): Unit = {
    //    val process = Process.liftOne((x: Int) => x * 2).repeated
    //    val list = process(Stream(1, 2, 3)).toList
    //    println(list)
    //    val process = Process.filter((x: Int) => x % 2 == 0)
    //    val list = process(Stream(1, 2, 3, 4)).toList
    //    println(list)
    //val list = Process.sum(Stream(1,2,3,4)).toList
    //    val list = Process.take(3)(Stream(1,2,3,4)).toList
    //    println(list)
    //    val list = Process.drop(2)(Stream(1, 2, 3, 4)).toList
    //    println(list)
    //      val list = Process.takeWhile((x:Int)=>x%2==0)(Stream(1,5,2,4,9,10,8,7,5)).toList
    //      println(list)
    //    val list = Process.dropWhile((x: Int) => x % 2 == 0)(Stream(1, 5, 2, 4, 9, 10, 8, 7, 5)).toList
    //    println(list)
    //    val list = Process.count(Stream(1, 2, 3, 4)).toList
    //    println(list)
    //    val list = Process.mean(Stream(1, 2, 3, 4)).toList
    //    println(list)
    val process1 = Process.filter((x: Int) => x % 2 == 0)
    val process2 = Process.liftOne((x: Int) => x * 2)
    val process = process1 ++ process2
    //这里为啥两个结果是一样
    val list = process(Stream(1, 5, 2, 4, 9, 10, 8, 7, 5)).toList
    val list1 = process1(Stream(1, 5, 2, 4, 9, 10, 8, 7, 5)).toList
    println(list)
    println(list1)
  }
}
