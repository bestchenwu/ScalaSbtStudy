package diveIntoScala.unit4_objectProgram

/**
  * 如果我们定义一个没有任何方法,只声明的特质,那么就创建了一个不继承scalaObject的纯java接口
  */
trait PureAbstract {
  def myAbstractMethod(): Unit

  /**
    * 加上了这个方法,特质就变成了abstract class
    *
    * @return
    */
  def hello(): String = {
    "abc"
  }
}
