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

  /**
    * 循环对流式里的所有元素循环调用f,得到最终结果
    *
    * @param z
    * @param f
    * @tparam B
    * @return B
    * @author chenwu on 2019.3.3
    */
  def foldRight[B](z: => B)(f: (A, B) => B): B = {
    this match {
      case Cons(h, t) => f(h(), t().foldRight(z)(f))
      case _ => z
    }
  }

  /**
    * 使用foldRight来判断是否存在p函数的方法
    *
    * @param p
    * @return Boolean
    * @author chenwu on 2019.3.3
    */
  def exists2(p: A => Boolean): Boolean = {
    //惰性求值,如果p(a)符合,则不对b表达式求值了
    foldRight(false)((a, b) => p(a) || b)
  }

  /**
    * 检查Stream中的所有元素是否与给定的断言匹配<br/>
    * 遇到不匹配的值应该立即终止遍历
    *
    * @param p
    * @return Boolean
    * @author chenwu on 2019.3.3
    */
  def forAll(p: A => Boolean): Boolean = {
    this match {
      case Cons(h, t) => {
        if (!p(h())) {
          false
        } else {
          t().forAll(p)
        }
      }
      case Empty => true
    }
  }

  /**
    * 使用foldRight来实现takeWhile
    *
    * @param f
    * @return Stream
    * @author chenwu on 2019.3.3
    */
  def takeWhile2(f: A => Boolean): Stream[A] = {

    foldRight(Stream.empty[A])((h, t) => {
      if (f(h)) {
        Stream.cons(h, t)
      } else {
        Stream.empty[A]
      }
    })

  }

  def headOption2(): Option[A] = {
    //t不传入进去,则循环只会执行一次
    foldRight(None: Option[A])((h, t) => Some(h))
  }

  def map[B](f: A => B): Stream[B] = {
    foldRight(Stream.empty[B])((h, t) => Stream.cons(f(h), t))
  }

  def filter(f: A => Boolean): Stream[A] = {
    foldRight(Stream.empty[A])((h, t) => {
      if (f(h)) {
        Stream.cons(h, t)
      } else {
        //如果不满足条件,z再次被初始化为empty
        t
      }
    })
  }

  /**
    * 添加新的stream
    *
    * @param s
    * @tparam B
    * @return Stream
    * @author chenwu on 2019.3.3
    */
  def append[B >: A](s: => Stream[B]): Stream[B] = {
    foldRight(s)((h, t) => Stream.cons(h, t))
  }

  def flatMap[B](f: A => Stream[B]): Stream[B] = {
    foldRight(Stream.empty[B])((h, t) => f(h) append t)
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

  val ones: Stream[Int] = Stream.cons(1, ones)

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

  /**
    * 返回一个只包含常量的Stream
    *
    * @param a
    * @tparam A
    * @return
    * @author chenwu on 2019.3.5
    */
  def constant[A](a: A): Stream[A] = {
    //对一个变量添加lazy修饰符,会使scala延迟求值,直到变量第一次被引用的时候
    // 如果不添加lazy修饰符,会报forward reference extends over definition of value错误
    // todo:--引用在求值计算之前的错误?
    lazy val constantStream: Stream[A] = Stream.cons(a, constantStream)
    constantStream
  }

  /**
    * 生成整数无限流,从n开始,然后n+1,n+2,...
    *
    * @param n
    * @return
    */
  def from(n: Int): Stream[Int] = {
    cons(n, from(n + 1))
  }

  /**
    * 生成0,1,1,2,3,5,8...等的数列
    *
    * @return
    */
  def fibs(): Stream[Int] = {

    def loop(a: Int, b: Int): Stream[Int] = {
      cons(a, loop(b, a + b))
    }

    loop(0, 1)
  }
}