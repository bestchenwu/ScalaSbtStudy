package FunctionProgramming.exception

import scala.collection.mutable.ArrayBuffer

sealed trait Option[+A] {

  def map[B](f: A => B): Option[B] = {
    this match {
      case Some(get) => Some(f(get))
      case None => None
    }
  }

  //flatMap与map的区别就是flatMap的调用参数类型依然是Option类
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
    this map (Some(_)) getOrElse ob
  }

  def filter(f: A => Boolean): Option[A] = {
    this match {
      case Some(get) if (f(get)) => this
      case None => None
    }
  }


}

case class Some[+A](get: A) extends Option[A]

case object None extends Option[Nothing]

object Option {

  /**
    * 返回option列表里的所有元素的值<br/>
    * 只要option列表里出现了一个None即整体返回None
    *
    * @param a
    * @tparam A
    * @return Option
    * @author chenwu on 2019.2.24
    */
  def sequence[A](a: List[Option[A]]): Option[List[A]] = {

    if (a.isEmpty) {
      None
    }

    val newBuffer = ArrayBuffer[A]()

    def loop(head: Option[A], tail: List[Option[A]]): Option[List[A]] = {
      head match {
        case Some(value) => {
          newBuffer.append(value);
          if (tail.isEmpty) {
            Some(newBuffer.toList)
          } else {
            loop(tail.head, tail.tail)
          }
        }
        case None => None
      }
    }

    loop(a.head, a.tail)

  }

  /**
    * 对列表a里的每一个元素都调用f函数,如果返回的值为None,则整体结果也为None<br/>
    * 否则将返回值作为some,提取里面的值添加到list里
    *
    * @param a
    * @param f
    * @tparam A
    * @tparam B
    * @return
    */
  def travese[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = {
    if (a.isEmpty) {
      None
    }

    val newBuffer = ArrayBuffer[B]()

    def loop(head: A, tail: List[A]): Option[List[B]] = {
      val bOption = f(head)
      bOption match {
        case Some(bValue) => {
          newBuffer.append(bValue)
          if (tail.isEmpty) {
            Some(newBuffer.toList)
          } else {
            loop(tail.head, tail.tail)
          }
        }
        case None => None
      }
    }

    loop(a.head, a.tail)

  }
}