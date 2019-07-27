package diveIntoScala.unit7_implicit_typeSystem

import HList._

object HListTest {

  def main(args: Array[String]): Unit = {
    //左边的::表示类型定义
    //右边的::表示HNil的操作符 因为scala是从右至左进行编译
    val x:(String::Int::Boolean::HNil) = "hi"::5::false::HNil
    println(x)
    //这里的::表示Hcons值
    val one::two::three::HNil = x
    println(one)
    println(two)
    println(three)
  }
}
