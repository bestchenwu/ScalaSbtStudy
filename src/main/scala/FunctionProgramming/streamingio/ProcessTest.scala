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
    val list = Process.drop(2)(Stream(1, 2, 3, 4)).toList
    println(list)
  }
}
