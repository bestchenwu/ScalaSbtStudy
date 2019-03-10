package FunctionProgramming.parallelism

object ParallelismTest {

  /**
    * 二分法并行求和
    *
    * @param ints
    * @return Int
    * @author chenwu on 2019.3.10
    */
  def sum(ints: IndexedSeq[Int]): Int = {
    if (ints.size <= 1) {
      ints.headOption.getOrElse(0)
    } else {
      val (l1, l2) = ints.splitAt(ints.size / 2)
      sum(l1) + sum(l2)
    }

  }
}
