package diveIntoScala.unit7_implicit_typeSystem

import HList._

object HListTest {

  def main(args: Array[String]): Unit = {
    val x:(String::Int::Boolean::HNil) = "hi"::5::false::HNil
    println(x)
    val one::two::three::HNil = x
    println(one)
    println(two)
    println(three)
  }
}
