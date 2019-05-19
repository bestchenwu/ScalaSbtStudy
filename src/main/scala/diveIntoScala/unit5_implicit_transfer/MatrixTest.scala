package diveIntoScala.unit5_implicit_transfer

object MatrixTest {

  def main(args: Array[String]): Unit = {
    val matrix = new Matrix(Array(Array(1.3, 2.5, 3.1), Array(5.2, 4.8, 2.7)))
    println(matrix)
    //println(matrix.columnByIndex(2).foreach(println))
  }
}
