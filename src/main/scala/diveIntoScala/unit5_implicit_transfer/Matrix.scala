package diveIntoScala.unit5_implicit_transfer

import scala.collection.mutable.ArrayBuffer

/**
  * Array的第一个维度代表row,第二个维度代表column
  *
  * @param array
  * @author chenwu on 2019.5.19
  */
class Matrix(val array: Array[Array[Double]]) {

  /**
    * 获取第几行
    *
    * @param index
    * @return
    * @author chenwu on 2019.5.19
    */
  def rowByIndex(index: Int) = {
    array(index)
  }

  /**
    * 获取第几列
    *
    * @param index
    * @return
    * @author chenwu on 2019.5.19
    */
  def columnByIndex(index: Int): Array[Double] = {
    val buffer = ArrayBuffer[Double]()
    array.foldLeft(buffer)((buffer,rowArray) => {
      buffer.append(rowArray(index))
      buffer
    })
    buffer.toArray
  }

  lazy val rowSize = array.size
  lazy val columnSize = if (rowSize > 0) array(0).size else 0

  override def toString: String = {
    //从左往右
    array.foldLeft("")((msg, array) => {
      msg + array.mkString("\n|", "|", "|")
    })
    //从右往左
    //    array.foldRight("")((array1, msg) => {
    //      msg + array1.mkString("\n|", "|", "|")
    //    })
  }
}
