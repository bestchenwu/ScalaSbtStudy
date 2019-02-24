package FunctionProgramming.exception

sealed trait Either[+A,+E]

/**
  * left表示失败结果,value表示失败的描述
  *
  * @param value
  * @tparam E
  * @author chenwu on 2019.2.24
  */
case class Left[+E](value:E) extends Either[Nothing,E]

/**
  * right表示正确结果,value表示正确的值
  *
  * @param value
  * @tparam A
  * @author chenwu on 2019.2.24
  */
case class Right[+A](value:A) extends Either[A,Nothing]

object Either{

  def mean(xs:Seq[Double]):Either[String,Double]={
    if(xs.isEmpty){
      Left("mean of empty list")
    }else{
      Right(xs.sum/xs.size)
    }
  }
}