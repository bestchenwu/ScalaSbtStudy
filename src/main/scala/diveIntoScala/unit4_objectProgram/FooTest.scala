package diveIntoScala.unit4_objectProgram

object FooTest {

  def main(args: Array[String]): Unit = {
      val foo:Foo = new Main()
      println(foo.someMethod())
      println(foo.newMethod())
  }
}
