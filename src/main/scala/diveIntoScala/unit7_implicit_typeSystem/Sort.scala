package diveIntoScala.unit7_implicit_typeSystem

trait Sort[A, B] {
  def sort(a: A): B
}

object SortUtil {

  /**
    * 定义一个隐式参数,可以让客户端自行传排序器
    *
    * @param col
    * @param sorter
    * @tparam A
    * @tparam B
    * @return
    */
  def sort[A, B](col: A)(implicit sorter: Sort[A, B]) = {
    sorter.sort(col)
  }
}
