package diveIntoScala.unit7_implicit_typeSystem

import diveIntoScala.unit7_implicit_typeSystem._

object ImplicitTest {

  def main(args: Array[String]): Unit = {
    val implicitAndType = new ImplicitAndType()
    //    //    val firstArray = new ImplicitAndType().first11(Array(1, 2, 3))
    //    //    firstArray.foreach(println)
    //    val x: Array[_] = Array(3, 2)
    //    val firstArray = implicitAndType.first11(x)
    //    firstArray.foreach(println)
    //这里用存在类型，编译器可以根据
    //    val checkArray = implicitAndType.checkIsBoolean[Int](List(3, 2, 4))(_%2==0)
    //    checkArray.foreach(println)
    //如果是调用def checkIsBoolean1[A](col: List[A], f: A => Boolean): List[A]这样的形式,则不可以编译
    //因为编译器这里无法推断出_的类型
    //implicitAndType.checkIsBoolean1(List(3, 2, 4),_%2==0)

    //如果这样调用,会导致运行失败,因为编译器无法根据一个参数获取方法的所有参数类型
    //    val list = implicitAndType.peek(List(5,8,9))
    //    println(list)
    //    val list = implicitAndType.peek[Int,List[Int]](List(5,8,9))
    //    println(list)
    //        val list = implicitAndType.peek1(List(5,8,9))
    //        println(list)

    //sum为scala.collection.TraversableOnce里定义的方法
    //def sum[B >: A](implicit num: Numeric[B]): B = foldLeft(num.zero)(num.plus)
    //这里在包对象unit7_implicit_typeSystem定义了隐式对象StringNumeric
//    val sumList = List("a", "b", "c").sum
//    println(sumList)

    val set = Set()
  }
}
