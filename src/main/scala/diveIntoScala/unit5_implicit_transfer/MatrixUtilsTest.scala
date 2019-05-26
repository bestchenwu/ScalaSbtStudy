package diveIntoScala.unit5_implicit_transfer

object MatrixUtilsTest {

  def main(args: Array[String]): Unit = {
    val x = new Matrix(Array[Array[Double]](Array(1.0, 2.0), Array(3.0, 4.0)))
    val y = new Matrix(Array[Array[Double]](Array(5.0, 6.0), Array(7.0, 8.0)))
    println(x)
    println(y)
    //implicit val ts = SingleThreadStragy
    //implicit val ts2 = MultiThreadStragy
    val multiplyMatrix = MatrixUtils.multiply(x, y)
    println(multiplyMatrix)
    //MatrixUtils.m
  }
}
