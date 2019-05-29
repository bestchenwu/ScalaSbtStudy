package diveIntoScala.unit6_type_system

object TypeTest2 {

  type T = {
    type U
    def bar: U
  }

  val baz1: Object = new {
    type U = String

    def bar: U = "hello world"
  }


  val baz: T = new {
    type U = String

    //如果没有def bar:U,则在scala2.11中也可以支持
    //加入了方法的继承后,只可以在scala2.12中支持
    //TODO:但还是有编译错误提示
      def bar: U = "hello world"
  }

  def test(f: T) = println(f.bar)

  def main(args: Array[String]): Unit = {
    //test(baz)
    test(baz)
    //test(typeOf(baz1.getClass))
  }
}
