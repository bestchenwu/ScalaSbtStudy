package FunctionProgramming.exception

sealed trait Either[+E,+A]{

  def map[B](f:A=>B):Either[E,B]={
      this match{
        case Right(value)=>Right(f(value))
        case Left(error)=>Left(error)
      }
  }

  /**
    * EE必须是E类型或者是E的父类型
    *
    * @param f
    * @tparam EE
    * @tparam B
    * @return
    */
  def flatMap[EE>:E,B](f:A=>Either[EE,B]):Either[EE,B]={
      //todo:
      null
  }
}

/**
  * left表示失败结果,value表示失败的描述
  *
  * @param value
  * @tparam E
  * @author chenwu on 2019.2.24
  */
case class Left[+E](value:E) extends Either[E,Nothing]

/**
  * right表示正确结果,value表示正确的值
  *
  * @param value
  * @tparam A
  * @author chenwu on 2019.2.24
  */
case class Right[+A](value:A) extends Either[Nothing,A]

object Either{

  def mean(xs:Seq[Double]):Either[String,Double]={
    if(xs.isEmpty){
      Left("mean of empty list")
    }else{
      Right(xs.sum/xs.size)
    }
  }


}