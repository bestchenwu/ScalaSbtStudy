package implictTest

case class Home(name: String)

case class Remote(name: String)

object Transport {

  //隐式参数
  def trans(transferName: String)(implicit home: Home, remote: Remote) = {
    println(s"I have transfered from ${remote.name} to ${home.name} by ${transferName}")
  }
}

object Address {

  implicit  val remote = Remote("shanghai")
  implicit  val home = Home("wuhan")
}

object TransferTest extends App {

  //这里定义double类型,但是用int值赋值,表示存在一个隐式转换将int转换为double
  //具体代码可参见Int.scala里int2double函数
  //  val x:Double = 3
  //如果直接这样定义,会报类型不匹配问题
  //val y:Int = 5.0
  //但是假如这样定义了转换函数,则可以将double类型转换为int
  //  implicit  def double2Int(x:Double) = {
  //      x.toInt
  //  }
  //  val y:Int = 5.0

  //  case class Rectangle(width: Int, height: Int) {
  //    override def toString: String = {
  //      s"Rectangle[width=${width},height=${height}]"
  //    }
  //  }
  //
  //  //val rectangle = Rectangle(3,4)
  //
  //  //如果定义隐式类,则编译器会生成如下的隐式转换函数
  //  //implicit def RectangleMaker(width: Int) = new RectangleMaker(width)
  //  implicit class RectangleMaker(width: Int) {
  //    def x(height: Int) = {
  //      Rectangle(width, height)
  //    }
  //  }
  //
  //  val newRectangle = 3 x 4
  //  //所以这里相当于在int值3上面，先初始化了RectangleMaker类，再调用了x函数
  //  println(newRectangle)
  import Address._
  Transport.trans("airplane")
}
