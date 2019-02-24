package FunctionProgramming.exception

import FunctionProgramming.exception.{Option => MyOption}

object Exercise {

  def mean(xs: Seq[Double]): MyOption[Double] = {
    if (xs.isEmpty) {
      None
    } else {
      Some(xs.sum / xs.size)
    }
  }

  /**
    * 计算一个序列的方差
    *
    * @param xs
    * @return Option 方差值
    * @author chenwu on 2019.2.24
    */
  def variance(xs: Seq[Double]): MyOption[Double] = {

    mean(xs) flatMap (m => mean(xs.map((x: Double) => math.pow(x - m, 2))))

    //    //计算均值
    //    val mean = xs.reduce(_ + _) / xs.size
    //
    //    val newSeq = xs.map((x: Double) => math.pow(x - mean, 2))
    //    Some(newSeq.sum / newSeq.size)
  }

  //参数类型是Option[A],返回类型是Option[B]
  def lift[A, B](f: A => B): Option[A] => Option[B] = _ map f

  def abs0: Option[Double] => Option[Double] = lift(math.abs)




  def main(args: Array[String]): Unit = {
    //      val seq = Seq(11.2,12.3,15.8)
    //      val result = variance(seq)
    //      print(result)
    val result1 = abs0(Some(-33))
    println(result1)
  }
}
