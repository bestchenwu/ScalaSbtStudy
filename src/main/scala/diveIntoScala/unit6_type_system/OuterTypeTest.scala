package diveIntoScala.unit6_type_system

class Outer {

  trait Inner

  def y = new Inner {}

  def foo(x: Inner) = null

  //todo:似乎在scala2.11之后取消了#运算符
  //def bar(x : X#Inner)=null
}

object Resource {
  //定义结构化的类型(具备close方法)
  type Resource = {
    def close()
  }

  def closeResource(r: Resource) = r.close()
}

object OuterTypeTest {

  def main(args: Array[String]): Unit = {
//    val x = new Outer
//    println(x.y)
      //这里system.in碰巧有一个close方法
      Resource.closeResource(System.in)
  }
}
