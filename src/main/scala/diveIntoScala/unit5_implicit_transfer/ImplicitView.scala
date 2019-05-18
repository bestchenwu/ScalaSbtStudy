package diveIntoScala.unit5_implicit_transfer

object ImplicitView extends App {

  //  trait Foo {
  //    def printFoo: String
  //  }
  //
  //  def foo(x: String) = println(x)
  //
  //  //foo(3)
  //  //建立了一个隐形视图 int=>String
  //  //通常用于表达式不符合编译器要求类型的场合,例如上文中的foo(3)
  //  implicit def intToString(x: Int) = x.toString
  //
  //  //现在调用foo(3)就能正确解析到了
  //  foo(3)
  //
  //  //或者用于编译器无法查到成员变量的情况
  //  implicit def strToFoo(str: String) = new Foo {
  //    override def printFoo: String = "I am a foo"
  //  }
  //
  //  println("haha".printFoo)

  object Holder {

    trait Foo

    trait Bar
    //这种方式的转换允许我们在库与库之间做匹配
    //典型例子就是scala.collection.JavaConversions
    implicit def fooToBar(foo: Foo) = new Bar {
      override def toString: String = "I am a bar from foo"
    }
  }

  import Holder._

  def bar(x: Bar) = println(x)

  val foo = new Foo {}
  bar(foo)
}
