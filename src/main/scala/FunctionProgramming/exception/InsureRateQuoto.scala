package FunctionProgramming.exception


object InsureRateQuoto {

  def insureRateQuoto(age: Int, numberOfSpped: Int): Double = {
    (age * numberOfSpped).toDouble
  }

  //参数类型是Option[A],返回类型是Option[B]
  def lift[A, B](f: A => B): Option[A] => Option[B] = _ map f

  def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = {
    a flatMap (aa => b map (bb => f(aa, bb)))

  }

  def map2ByFor[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = {
    //scala的for推导,编译器会将yield转换为flatmap,将aa <- a转换为map运算
    for {
      aa <- a
      bb <- b
    } yield f(aa, bb)
  }

  def insureRateQuoto(age: String, numberOfSpped: String): Option[Double] = {
    map2(Try(age.toInt), Try(numberOfSpped.toInt))(insureRateQuoto)
  }

  //def listInsureRateQuoto[A,B,C](f: (A,B)=>C):(Option[A],Option[B])=>Option[C]=(_,_) map f

  def Try[A](a: => A): Option[A] = {
    try {
      Some(a)
    } catch {
      case exception: Exception => None
    }
  }

  def safeInsureRateQuoto(age: String, numberOfSpped: String): Option[Double] = {

    insureRateQuoto(age, numberOfSpped)
  }

  def main(args: Array[String]): Unit = {
    val result = safeInsureRateQuoto("13", "15")
    print(result)
  }
}
