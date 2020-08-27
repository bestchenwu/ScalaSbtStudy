package akkaStudy.unit2_actor_parallize

object PartialFunctionTest {

  def main(args: Array[String]): Unit = {
    val par = new PartialFunction[Int, Int] {
      override def isDefinedAt(x: Int): Boolean = x % 2 == 0

      override def apply(v1: Int): Int = v1 + 1
    }
    val list = List(1, 3, 4, 8, 9)
    val list2 = list collect par
    println(list2)
  }
}
