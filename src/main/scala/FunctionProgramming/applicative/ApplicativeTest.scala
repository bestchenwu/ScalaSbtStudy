package FunctionProgramming.applicative

object ApplicativeTest {

  def main(args: Array[String]): Unit = {
    val applicative = new Applicative[Option] {
      override def unit[A](a: => A): Option[A] = Some(a)
    }
    //    val result = applicative.traverse(List("3", "5", "aa"))((x: String) => {
    //      try {
    //        Some(x.toInt)
    //      } catch {
    //        case e: Exception => Some(e.getMessage)
    //      }
    //    })


    //    val result  = applicative.sequence(List(Some(1),Some("aa")))
    //val result = applicative.replicateM(3, Some("abc"))
    val result = applicative.product(Some("abc"),Some("123"))
    print(result)

  }
}
