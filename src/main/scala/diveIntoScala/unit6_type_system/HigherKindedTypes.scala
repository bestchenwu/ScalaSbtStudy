package diveIntoScala.unit6_type_system

import scala.util.Random

/**
  * 类型参数和高阶类型
  *
  * @author chenwu on 2019.6.15
  */
object HigherKindedTypes {

  /**
    * 这称为高阶类型,也可以称为类型构造器<br/>
    * 例如下面的这个回调函数接受一个参数,但返回为unit
    *
    * @tparam T
    */
  type callback[T] = Function1[T, Unit]

  /**
    * M是未知的参数化类型,f表示具体化类型的M
    *
    * @param f
    * @tparam M
    * @return
    */
  def foo[M[_]](f:M[Int]) = f

  //val newCallBack = foo[callback]

  /**
    * 第一个A表示方法参数的类型,第二个A表示泛型类的类型
    *
    * @param a
    * @tparam A
    * @return
    */
  def randElement[A](a: Seq[A]): A = {
    if (a.isEmpty) {
      throw new IllegalArgumentException("a can't be empty")
    } else {
      a(Random.nextInt(a.length))
    }
  }

  def main(args: Array[String]): Unit = {
    //    val a = randElement(List(1, 2, 3))
    //    println(a)
    //val a = randElement(List())
    //编译器会把callback[Int]转换为(Int)=>Unit的函数
    val x: callback[Int] = x => println(s"x*2=${x * 2}")
    //x(1)
    foo[callback](x)(3)
  }
}
