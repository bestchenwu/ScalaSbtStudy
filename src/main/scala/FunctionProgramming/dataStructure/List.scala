package FunctionProgramming.dataStructure

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
      case Nil => 0
      case Cons(0.0, _) => 0
      case Cons(x, xs) => x * product(xs)
    }
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
      case Cons(h,t) if f(h) => dropWhile(t, f)
      case _ => list
    }
  }

  def appendList[A](a:List[A],b:List[A]):List[A]={
      a match{
        case Nil => b
        case Cons(x:A,xs:List[A])=>Cons(x,appendList(xs,b))
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
        val newList = Cons(newHead, xs); newList
      }
    }
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

  //  def printList[A](list: List[A]): String = {
  //    case Nil => ""
  //    case Cons(x:A, xs:List[A]) => x + "," + printList(xs)
  //  }

  def printList[A](list: List[A]): String = {
    list match {
      case Nil => ""
      case Cons(x: A, xs: List[A]) => x.toString() + "," + printList(xs)
    }

  }
}
