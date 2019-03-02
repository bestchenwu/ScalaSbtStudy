package FunctionProgramming.exception

object EitherTest {

  def main(args: Array[String]): Unit = {
    val list = List(0, 2, 4, 10)
    val f = (x: Int) => {
      if (x % 2 == 0) {
        Right(Math.log(x))
      } else {
        Left(s"${x} is not valid")
      }
    }
    val result = Either.traverse(list)(f)
    print(result)
  }
}
