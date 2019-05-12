package diveIntoScala.unit5_implicit_transfer

class ImplicitTransfer {

  /**
    * implicit如果作用于方法的变量上,则表明变量可以不传,它可以从方法体内的上下文寻找合适的变量
    *
    * @param x
    */
  def findInt(implicit x: Int) = {
    print(s"x=${x}")
  }
}

class Foo(x: Int) {
  def tmp = {
    //这种情况称为变量作用域遮挡
    //优先级顺序
    //本地的、 继承的 优先级最高
    //其次是显式导入
    //再其次是通配符导入
    //最后是同包不同源文件
    val x = 2
    x
  }
}

object ImplicitTransfer {

  def main(args: Array[String]): Unit = {
    //    val test = new ImplicitTransfer()
    //    implicit val y:Int = 5
    //    //如果加上这一句,运行时候会报ambious int值的错误
    //    //implicit val y1:Int = 10
    //    test.findInt
    val foo = new Foo(5)
    println(foo.tmp)
  }
}
