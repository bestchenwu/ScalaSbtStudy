package diveIntoScala.unit5_implicit_transfer

/**
  * 隐式作用域
  */
object ImplicitScope {

  trait Foo
  //这种模式等同于将newFoo放到伴生对象Foo里
  implicit def newFoo = new Foo{
    override def toString: String = "I am a new Foo"
  }
  object  Bar
  implicit def b:Bar.type =  Bar
//  object Foo {
//    implicit val x = new Foo {
//      override def toString: String = "I am a x "
//    }
//    implicit val list = List(new Foo{})
//  }

}

object Method {

  def main(args: Array[String]): Unit = {
    //import ImplicitScope.Foo
    //这个例子说明伴生对象里也属于隐式作用域
//    def printX(implicit anyFoo: Foo) = println(anyFoo)
//
//    printX
    //implicitly这个函数用于找出类型为List[ImplicitScope.Foo]的值 又称为类型类结构
    //implicitly[List[ImplicitScope.Foo]]
//    val x = implicitly[ImplicitScope.Foo]
//    println(x)
    //这个例子说明通过函数的定义也可以找到对应的隐式类型
      val bar = implicitly[ImplicitScope.Bar.type]
  }
}