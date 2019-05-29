package diveIntoScala.unit6_type_system


object TypeTest {

  trait T {
    //这里type U相当于接口的抽象类型
    type U

    def bar: U
  }

  //这里定义了结构化类型
  type M = {

    type U
    def eating():Unit
    //def bar:U
  }

  val m = new {

    type U = String

    def eating():Unit= {
      println("helloSpark")
    }

    //def bar:U = "hello world"
  }

  def g(m: M) = m.eating


  class Foo extends T {

    //这里定义了U等同于String的别名
    type U = String

    def bar: U = "hello,world"
  }

  def test(t: T) = t.bar

  class S1 {
    //this.type是scala中提供的一种机制,其指向当前对象的类型
    def fun(): this.type = this
  }

  class S2 extends S1 {
    def g(): this.type = this
  }

  def main(args: Array[String]): Unit = {
    //var s2 = new S2()
    //依靠type机制,所以可以做到链式编程的风格
    //print(s2.g().fun().g().fun())
    //test(baz)
    //    val foo = new Foo()
    //    println(test(foo))
    g(m)
    //    barTT(new {
    //      type U = String
    //      def bar:U = "hello String"
    //    })
  }
}
