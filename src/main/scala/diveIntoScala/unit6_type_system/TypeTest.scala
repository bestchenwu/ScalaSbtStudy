package diveIntoScala.unit6_type_system


object TypeTest {

  type T = {
    type U

    def bar: U
  }

  //  object Foo {
  //    type X = Int
  //
  //    def x: X = 5
  //
  //    type Y = String
  //
  //    def y: Y = "hello,world"
  //  }
  val baz: T = new T {
    type U = String

    def bar: String = "hello,world"
  }

  def test(t: T) = print(t.bar)

  def main(args: Array[String]): Unit = {
      test(baz)
  }
}
