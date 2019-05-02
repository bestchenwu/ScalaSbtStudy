package diveIntoScala.unit4_objectProgram

/**
  * 延迟初始化操作现在放在App类里面(从2.11版本开始)
  */
class DelayedInitTest extends App {

  var x: Option[Function0[Unit]] = None

  lazy val y: Int = 3

  /**
    * 定义在该方法体内的属性会被延迟初始化,直到对象被调用到才会执行
    *
    * @param cons
    */
  override def delayedInit(cons: => Unit) = {
    x = Some(() => cons)
  }

  def executeX(args: Array[String]): Unit = {
    x.foreach(_ ())
  }
}

object DelayedInitTest1 {

  def main(args: Array[String]): Unit = {
    val x = new DelayedInitTest {
      println("now i am intialized")
    }
    x.executeX(Array())
    //x.main(Array())
  }
}
