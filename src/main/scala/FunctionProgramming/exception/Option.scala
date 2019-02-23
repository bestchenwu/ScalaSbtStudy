package FunctionProgramming.exception

sealed trait Option[+A] {

  def map[B](f: A => B): Option[B] = {
    this match {
      case Some(get) => Some(f(get))
      case None => None
    }
  }

  def flatMap[B](f: A => Option[B]): Option[B] = {
    map(f) getOrElse None
  }

  /**
    * B >: A 表示B类型必须是A的父类型
    *
    * @param default
    * @tparam B
    * @return
    */
  def getOrElse[B >: A](default: => B): B = {
    this match {
      case Some(get) => get
      case None => default
    }
  }

  /**
    * 不对ob求值,除非必须
    *
    * @param ob
    * @tparam B
    * @return Option
    * @author chenwu on 2019.2.23
    */
  def orElse[B >: A](ob: => Option[B]): Option[B] = {
      this map(Some(_)) getOrElse ob
  }

  def filter(f: A => Boolean): Option[A] = {
    this match {
      case Some(get) if(f(get)) => this
      case None => None
    }
  }
}

case class Some[+A](get: A) extends Option[A]

case object None extends Option[Nothing]

object Option {

  def test(): Unit = {

  }
}