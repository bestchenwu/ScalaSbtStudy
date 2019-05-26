package diveIntoScala.unit5_implicit_transfer

/**
  * 矩阵工具类
  *
  * @author chenwu on 2019.5.26
  */
object MatrixUtils {

  /**
    * 计算两个矩阵的乘法
    *
    * @param x
    * @param y
    * @param trheadStragy
    * @return Matrix
    * @author chenwu on 2019.5.26
    */
  def multiply(x: Matrix, y: Matrix)(implicit threadStragy: ThreadStragy = SingleThreadStragy): Matrix = {
    //这里要假定x的行维度==y的列维度
    assert(x.rowSize == y.columnSize)
    //这里需要对buffer先初始化
    val buffer =  Array.fill(x.rowSize)(Array.fill(y.rowSize)(0.0))

    /**
      * 将矩阵的第几行和第几列相乘
      *
      * @param row
      * @param column
      * @return Double
      * @author chenwu on 2019.5.26
      */
    def multi(row: Int, column: Int): Unit = {
      val zipArray = x.rowByIndex(row).zip(y.columnByIndex(column))
      val result = zipArray.foldLeft(0.0)((z, item) => z + item._1 * item._2)
      buffer(row)(column) = result

    }

    val computations = for {
      //这里使用row <- x.rowSize相当于只是把rowSize赋值给x
      //只有使用row <- 0 until x.rowSize才是创建了一个循环
      row <- 0 until x.rowSize
      column <- 0 until y.columnSize
    } yield threadStragy.exectue(() => multi(row, column))

    computations.foreach(_=>())
    new Matrix(buffer)
  }
}
