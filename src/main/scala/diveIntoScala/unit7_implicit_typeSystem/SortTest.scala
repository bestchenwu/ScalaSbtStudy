package diveIntoScala.unit7_implicit_typeSystem

object SortTest {
  def main(args: Array[String]): Unit = {
      type X[T <: TBool[Any]] = T#If[String,Int,Any]
      val x:X[TTrue[Any]] = "hi"
      println(s"x=${x}")
  }
}
