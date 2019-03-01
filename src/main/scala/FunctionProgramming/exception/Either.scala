package FunctionProgramming.exception

sealed trait Either[+E, +A] {

  def map[B](f: A => B): Either[E, B] = {
    this match {
      case Right(value) => Right(f(value))
      case Left(error) => Left(error)
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
  def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] = {
    this match {
      case Left(e) => Left(e)
      case Right(value) => f(value)
    }
  }

  def orElse[EE >: E, B >: A](b: => Either[EE, B]): Either[EE, B] = {
    this match {
      case Left(e) => Left(e)
      case Right(value) => b
    }
  }

  def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] = {
    this match {
      case Left(e) => Left(e)
      case Right(value) => {
        b match {
          case Left(e1) => Left(e1)
          case Right(value1) => Right(f(value, value1))
        }
      }
    }
  }

  /**
    * 将列表es里的所有元素值提取出来组成List返回<br/>
    * 如果列表中有一个值为错误,则整体返回错误
    *
    * @param es
    * @tparam E
    * @tparam A
    * @return Either[E,List[A]]
    * @author chenwu on 2019.3.1
    */
  def sequence[E, A](es: List[Either[E, A]]): Either[E, List[A]] = {
    //todo:
    null
  }

  /**
    * 对列表里的每一个元素循环调用f函数,如果结果为Right，则加入列表中<br/>
    * 只要有一个为Wrong，则返回Left
    *
    * @param as
    * @param f
    * @tparam E
    * @tparam A
    * @tparam B
    * @return Either[E,List[B]]
    * @author chenwu on 2019.3.1
    */
  def traverse[E,A,B](as:List[A])(f:A=>Either[E,B]):Either[E,List[B]]={
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
case class Left[+E](value: E) extends Either[E, Nothing]

/**
  * right表示正确结果,value表示正确的值
  *
  * @param value
  * @tparam A
  * @author chenwu on 2019.2.24
  */
case class Right[+A](value: A) extends Either[Nothing, A]

object Either {

  def mean(xs: Seq[Double]): Either[String, Double] = {
    if (xs.isEmpty) {
      Left("mean of empty list")
    } else {
      Right(xs.sum / xs.size)
    }
  }


}