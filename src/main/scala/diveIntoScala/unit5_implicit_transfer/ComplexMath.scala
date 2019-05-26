package diveIntoScala.unit5_implicit_transfer

/**
  * 复数的实现类
  *
  * @param x
  * @param y
  * @author chenwu on 2019.5.26
  */
case class ComplexMath(x: Double, y: Double) {

  def +(other: ComplexMath): ComplexMath = {
    ComplexMath(this.x + other.x, this.y + other.y)
  }

  def *(other: ComplexMath): ComplexMath = {
    ComplexMath(this.x * other.x, this.y * other.y)
  }

  override def toString: String = {
    s"ComplexMath x=${x},y=${y}"
  }
}
