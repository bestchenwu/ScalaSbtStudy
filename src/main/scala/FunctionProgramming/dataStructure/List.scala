package FunctionProgramming.dataStructure

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

//sealed表示该类的所有定义都需要在本文件里完成
sealed trait List[+A]

//空数组 Nothing是所有类型的子类型
case object Nil extends List[Nothing]

case class Cons[+A](head: A, tail: List[A]) extends List[A]

//包含创建List和List的操作函数
object List {


  /**
    * 对一个数组里的所有元素循环累加
    *
    * @param ints
    * @return int
    */
  def sum(ints: List[Int]): Int = {
    ints match {
      case Nil => 0
      case Cons(x, xs) => x + sum(xs)
    }
  }

  def product(doubles: List[Double]): Double = {
    doubles match {
      case Nil => 1.0
      case Cons(0.0, _) => 0
      case Cons(x, xs) => x * product(xs)
    }
  }

  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = {
    as match {
      case Nil => z
      case Cons(x, xs) => foldRight(xs, f(x, z))(f)
    }
  }


  def sum2(ints: List[Int]): Int = {
    foldRight(ints, 0)((x: Int, y: Int) => x + y)
  }

  def product2(doubles: List[Double]): Double = {
    foldRight(doubles, 1.0)(_ * _)
  }

  def length[A](list: List[A]): Int = {
    foldRight(list, 0)((x: A, y: Int) => y + 1)
  }

  @tailrec
  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = {
    as match {
      case Nil => z
      case Cons(x, xs) => foldLeft(xs, f(z, x))(f)
    }

  }

  def sum3(ints: List[Int]): Int = {
    foldLeft(ints, 0)((x: Int, y: Int) => x + y)
  }

  /**
    * 将列表反转
    *
    * @param as
    * @tparam A
    * @return List[A]
    * @author chenwu on 2019.2.16
    */
  def reverse[A](as: List[A]): List[A] = {
    val vector = List[A]()
    foldLeft(as, vector)((x: List[A], y: A) => Cons(y, x))
  }

  def append2[A](x: List[A], y: List[A]): List[A] = {

    foldRight(x, y)(Cons(_, _))
  }

  /**
    * 删除头部元素
    *
    * @param list
    * @tparam A
    * @author chenwu on 2019.2.8
    */
  //  def tail[A](list: List[A]): List[A] = {
  //    case Nil => Nil
  //    case Cons(x:A, xs:List[A]) => xs
  //  }

  def tail[A](list: List[A]): List[A] = {
    list match {
      case Nil => Nil
      case Cons(x: A, xs: List[A]) => xs
    }

  }

  /**
    * 从列表中拿走前n个元素
    *
    * @param list
    * @param n
    * @tparam A
    * @return { @link List}
    * @author chenwu on 2019.2.14
    */
  def drop[A](list: List[A], n: Int): List[A] = {
    if (n == 0) {
      list
    } else {
      list match {
        case Nil => Nil
        case Cons(x: A, t) => drop(t, n - 1)
      }
    }
  }

  /**
    * 删除列表中前缀全部符合函数f的元素(不是所有符合条件)
    *
    * @param list
    * @param f
    * @tparam A
    * @return List[A]
    * @author chenwu on 2019.2.14
    */
  def dropWhile[A](list: List[A], f: A => Boolean): List[A] = {

    list match {
      //利用了scala case语句里可以带条件表达式的特点
      case Cons(h, t) if f(h) => dropWhile(t, f)
      case _ => list
    }
  }

  def appendList[A](a: List[A], b: List[A]): List[A] = {
    a match {
      case Nil => b
      case Cons(x: A, xs: List[A]) => Cons(x, appendList(xs, b))
    }
  }

  /**
    * 置换list的第一个元素
    *
    * @param list
    * @param newHead
    * @tparam A
    * @return List[A]
    * @author chenwu on 2019.2.13
    */
  def setHead[A](list: List[A], newHead: A) = {
    list match {
      case Nil => Nil
      case Cons(x: A, xs: List[A]) => {
        val newList = Cons(newHead, xs);
        newList
      }
    }
  }

  /**
    * 获取原数组里除了最后一个元素之外的所有元素
    *
    * @param list
    * @tparam A
    * @return List[A]
    * @author chenwu on 2019.2.15
    */
  def init[A](list: List[A]): List[A] = {

    list match {
      case Nil => list
      case Cons(h, Nil) => Nil
      case Cons(h, t: List[A]) => {
        new Cons(h, init(t))
      }
    }
  }

  /**
    * 利用ListBuffer循环添加元素保留元素
    *
    * @param list
    * @tparam A
    * @return List[A]
    * @author chenwu on 2019.2.15
    */
  def init2[A](list: List[A]): List[A] = {

    val listBuffer = ListBuffer[A]()

    def loop(list: List[A]): List[A] = {
      list match {
        case Nil => list
        case Cons(h, Nil) => apply(listBuffer: _*)
        case Cons(h, t: List[A]) => {
          listBuffer.append(h);
          loop(t)
        }
      }
    }

    loop(list)
  }

  /**
    * 对列表里的每个元素加一
    *
    * @param list
    * @return List[Int]
    * @author chenwu on 2019.2.17
    */
  def add1(list: List[Int]): List[Int] = {

    list match {
      case Nil => list
      case Cons(x, xs) => Cons(x + 1, add1(xs))
    }
  }

  /**
    * 将double list里的每一个元素都转化成为String
    *
    * @param doubles
    * @return List[String]
    * @author chenwu on 2019.2.18
    */
  def transToString(doubles: List[Double]): List[String] = {
    var strings = ListBuffer[String]()

    def loop(doubles: List[Double]): List[String] = {
      doubles match {
        case Nil => apply(strings: _*)
        case Cons(x, xs) => {
          strings.append(x.toString);
          loop(xs)
        }
      }
    }

    loop(doubles)
  }

  def transToString2(doubles: List[Double]): List[String] = {
    doubles match {
      case Nil => Nil
      case Cons(x, xs) => Cons(x.toString, transToString2(xs))
    }
  }

  def map[A, B](as: List[A])(f: A => B): List[B] = {
    as match {
      case Nil => Nil
      case Cons(x, xs) => Cons(f(x), map(xs)(f))
    }
  }

  //  def filter[A](as:List[A])(f:A=>Boolean):List[A]={
  //    as match {
  //      case Cons(x, xs) if(f(x))=>filter(xs)(f)
  //      case _ => as
  //    }
  //  }

  /**
    * 根据函数f 过滤列表as
    *
    * @param as
    * @param f
    * @tparam A
    * @return List[A]
    * @author chenwu on 2019.2.18
    */
  //todo:是否有不利用ListBuffer的方式
  def filter[A](as: List[A])(f: A => Boolean): List[A] = {
    val newList = ListBuffer[A]()

    def loop(as: List[A]): List[A] = {

      as match {
        case Cons(x, xs) => {
          if (!f(x)) {
            newList.append(x)
          }
          loop(xs)
        }
        case Nil => apply(newList: _*)
      }
    }

    loop(as)
  }

  /**
    * 对列表as里的所有元素都应用f函数,并展开成List
    *
    * @param as
    * @param f
    * @tparam A
    * @tparam B
    * @return List[B]
    * @author chenwu on 2019.2.18
    */
  def flatMap[A, B](as: List[A])(f: A => List[B]): List[B] = {
    //todo:有不利用appendList()方法来实现的方式吗?
    var resultList = List[B]()

    def loop(as: List[A]): List[B] = {
      as match {

        case Nil => resultList
        case Cons(x, xs) => {
          val newList = f(x);
          resultList = appendList(resultList, newList);
          loop(xs)
        }
      }
    }

    loop(as)
  }

  /**
    * 利用flatmap函数来实现filter
    *
    * @param as
    * @param f
    * @tparam A
    * @return List[B]
    * @author chenwu on 2019.2.20
    */
  def filter2[A](as: List[A])(f: A => Boolean): List[A] = {
    flatMap(as)((x: A) => {
      if (f(x)) {
        Cons[A](x, Nil)
      } else {
        Nil
      }
    })
  }

  /**
    * 调用函数f对列表a,b的元素逐一运算得到新列表
    *
    * @param a
    * @param b
    * @param f
    * @tparam A
    * @return List[A]
    * @author chenwu on 2019.2.20
    */
  def zipWith[A](a: List[A], b: List[A])(f: (A, A) => A): List[A] = {
    var resultList = List[A]()

    def loop(a: List[A], b: List[A]): List[A] = {
      a match {
        case Nil => resultList
        case Cons(ax, axs) => {
          b match {
            case Nil => resultList
            case Cons(bx, bxs) => {
              val newA = f(ax, bx)
              resultList = Cons(newA, resultList)
              loop(axs, bxs)
            }
          }
        }
      }
    }

    reverse(loop(a, b))
  }

  /**
    * 内置函数 可以使用List(A)来初始化<br/>
    * 这里A* 代表一个或者多个参数列表
    *
    * @param a
    * @tparam A
    * @return
    */
  def apply[A](a: A*): List[A] = {
    if (a.isEmpty) {
      Nil
    } else {
      //将尾部元素转换为可变参数列表继续递归调用
      Cons(a.head, apply(a.tail: _*))
    }
  }

  def printList[A](list: List[A]): String = {
    list match {
      case Nil => ""
      case Cons(x: A, xs: List[A]) => x.toString() + "," + printList(xs)
    }

  }
}
