package diveIntoScala.unit7_implicit_typeSystem

import scala.reflect.ClassTag

class ImplicitAndType[B] {

  /**
    * 这个方法定义表达两层意思
    *
    * 1、x是A类型
    * 2、在调用地方必须存在隐式转换A=>B
    *
    * @param x
    * @tparam A
    * @return
    */
  //这称为隐式类型约束
  def foo[A <% B](x: A) = x

  //上述方法等价于下述表达式:
  def bar[A](x: A)(implicit $evid: A => B): A = x

  /**
    * 这里表示必须有一个A类型的x<br/>
    * 同时必须有一个隐式值B类型
    *
    * @param x
    * @tparam A
    * @return
    */
  //def foo1[A: B](x: A): A = x

  def first[T](x: Traversable[T]) = (x.head, x)

  //def bar1[A](x:A)(implicit $evid:B[A])

  //这里试图获取数组x里的第一个元素 并组成一个新数组
  //def first1[A](x: Array[A]) = Array(x(0))
  //在scala2.11中 用了classTag来代替ClassManifest 这样A就具备了x的泛型类型
  def first11[A: ClassTag](x: Array[A]) = Array(x(0))

  def checkIsBoolean[A](col: List[A])(f: A => Boolean): List[A] = {
    col.filter(f)
  }

  def checkIsBoolean1[A](col: List[A], f: A => Boolean): List[A] = {
    col.filter(f)
  }

  def peek[A, C <: Traversable[A]](c: C) = (c.head, c)

  //todo:这里表示如果C是Traversable的子类型，编译器就可以顺带推断出A的类型
  def peek1[A, C](c: C)(implicit ev: C <:< Traversable[A]) = (c.head, c)


}
