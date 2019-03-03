package FunctionProgramming.laziness

import scala.collection.mutable.ArrayBuffer

/**
  * 流式计算
  *
  * @tparam A
  * @author chenwu on 2019.3.3
  */
sealed trait Stream[+A] {

  /**
    * 获取stream的最头部元素
    *
    * @tparam A
    * @return
    * @author chenwu on 2019.3.3
    */
  def headOption: Option[A] = this match {
    case Empty => None
    case Cons(h, _) => Some(h())
  }

  /**
    * 将stream转换为list
    *
    * @return List
    * @author chenwu on 2019.3.3
    */
  def toList: List[A] = {
    val arrayBuffer = ArrayBuffer[A]()

    def loop(stream: Stream[A]): List[A] = {
      stream match {
        case Empty => arrayBuffer.toList
        case Cons(h, t) => {
          arrayBuffer.append(h())
          loop(t())
        }
      }
    }

    loop(this)
  }

  /**
    * 获取Stream中的前n个元素
    *
    * @param n
    * @return List
    * @author chenwu on 2019.3.3
    */
  def take(n: Int): List[A] = {
    val arrayBuffer = ArrayBuffer[A]()

    def loop(stream: Stream[A], n: Int): List[A] = {
      stream match {
        case Empty => arrayBuffer.toList
        case Cons(h, t) => {
          if (n == 0) {
            arrayBuffer.toList
          } else {
            arrayBuffer.append(h())
            loop(t(), n - 1)
          }
        }
      }
    }

    loop(this, n)
  }

  /**
    * 返回Stream中的第n个元素之后的所有元素
    *
    * @param n
    * @return Stream[A]
    * @author chenwu on 2019.3.3
    */
  def drop(n: Int): Stream[A] = {
    this match {
      case Empty => Empty
      case Cons(h, t) => {
        if (n <= 0) {
          Cons(h, t)
        } else {
          t().drop(n - 1)
        }
      }
    }
  }

  /**
    * 返回Stream中从起始元素连续满足给定断言的所有元素
    *
    * @param f
    * @return Stream[A]
    * @author chenwu on 2019.3.3
    */
  def takeWhile(f: A => Boolean): Stream[A] = {
    val arrayBuffer = ArrayBuffer[A]()

    def loop(stream: Stream[A]): Stream[A] = {
      stream match {
        case Empty => Stream.apply(arrayBuffer: _*)
        case Cons(h, t) => {
          val a = h()
          if (f(a)) {
            arrayBuffer.append(a)
            loop(t())
          } else {
            //表示当前元素不满足断言,中止循环
            Stream.apply(arrayBuffer: _*)
          }
        }
      }
    }

    loop(this)
  }

  /**
    * 判断集合里面是否包含符合p函数的值<br/
    * 这里p() || exists 属于惰性化,分离了求值表达式和它的描述
    *
    * @param p
    * @return
    * @author chenwu on 2019.3.3
    */
  def exists(p: A => Boolean): Boolean = {
    this match {
      case Cons(h, t) => p(h()) || t().exists(p)
      case _ => false
    }
  }
}

case object Empty extends Stream[Nothing]

/**
  * 流式计算
  * （） => A 函数字面量表达方法,表示接受0个参数,返回A
  *
  * @param h 求值函数,得到A
  * @param t 求值函数,得到下一个流
  * @tparam A
  * @author chenwu on 2019.3.2
  */
case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {

  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    //对惰性求值的函数做延迟初始化
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  //创建空stream的构造函数
  def empty[A]: Stream[A] = Empty

  /**
    * 创建一个流式器
    *
    * @param a
    * @tparam A
    * @return Stream
    * @author chenwu on 2019.3.2
    */
  def apply[A](a: A*): Stream[A] = {
    if (a.isEmpty) {
      empty
    } else {
      cons(a.head, apply(a.tail: _*))
    }
  }


}