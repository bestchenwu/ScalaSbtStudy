package FunctionProgramming.localeffects

object LocalEffects {
  def quickSort(xs: List[Int]): List[Int] = if (xs.isEmpty) xs else {
    val array = xs.toArray

    def swap(i: Int, j: Int) = {
      val tmp = array(i)
      array(i) = array(j)
      array(j) = tmp
    }

    /**
      * 保证从n到r的元素都小于array(r）,所以相当于对一个分区进行排序
      *
      * @param n
      * @param r
      * @return Int  返回r+1，表示当前小分区已经完成排序
      */
    def partition(n: Int, r: Int) = {
      val pivotVal = array(r)
      var j = n
      for (i <- n to r) {
        if (array(i) < pivotVal) {
          swap(i, j)
          j += 1
        }
      }
      swap(j, r)
      j
    }

    def loop(n: Int, r: Int): Unit = if (n < r) {
      val pi = partition(n, r)
      loop(0, pi - 1)
      loop(pi + 1, r)
    }
    loop(0,array.size-1)
    array.toList
  }
}
