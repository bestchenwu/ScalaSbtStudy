package FunctionProgramming.laziness

object StreamTest {

  def main(args: Array[String]): Unit = {
    val stream = Stream.apply(10,1, 3, 5, 8)
    //    val list = stream.take(2)
    //    println(list)
    //val result = stream.drop(8)
    //print(result.toList)
    //val result = stream.toList
    //print(result)
    val result = stream.takeWhile(_ % 2 != 0).toList
    print(result)
  }
}
