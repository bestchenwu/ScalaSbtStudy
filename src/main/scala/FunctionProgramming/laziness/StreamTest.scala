package FunctionProgramming.laziness

object StreamTest {

  def main(args: Array[String]): Unit = {
    val stream = Stream.apply(1, 10, 3)
    //    val list = stream.take(2)
    //    println(list)
    //val result = stream.drop(8)
    //print(result.toList)
    //val result = stream.toList
    //print(result)
    //    val result = stream.takeWhile(_ % 2 != 0).toList
    //    print(result)
    //val result = stream.exists2(_ % 2 == 0)
    //    val result = stream.forAll(_ % 2 != 0)
    //    print(result)
    //      val streamB = stream.map((x:Int)=>x*2)
    //      print(streamB.toList)
    //    val stream2 = Stream.apply(11, 12, 13)
    //    val result = stream.append(stream2)
    //    print(result.toList)
    //无限流
    //print(Stream.ones.take(3))
    //      val abcStream = Stream.constant[String]("abc")
    //      print(abcStream.take(5))
    //    val fibsStream = Stream.fibs()
    //    print(fibsStream.take(5))
    //      val unfoldStream = Stream.unfold[Int,Int](1)((x:Int)=>Option(x,x*2))
    //      print(unfoldStream.take(5))
    //print(Stream.fibsInUnfold().take(5))
    val list = Stream.tails(stream).toList
    for (streamItem <- list) {
      println(streamItem.toList)
    }

  }
}
