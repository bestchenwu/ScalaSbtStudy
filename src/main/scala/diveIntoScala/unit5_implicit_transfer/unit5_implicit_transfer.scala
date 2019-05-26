package diveIntoScala.unit5_implicit_transfer

/**
  * 在scala中通常将共享接口以及匿名转换方法或者匿名变量放在<br>
  * 与包名同名的对象中
  *
  * @author chenwu on 2019.5.26
  */
package object unit5_implicit_transfer {
  val i = ComplexMath(0, 1.0)

  implicit def transfer(x:Double):ComplexMath={
    ComplexMath(x,0.0)
  }
}
