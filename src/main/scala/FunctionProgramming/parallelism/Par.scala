package FunctionProgramming.parallelism
import java.util.concurrent._
import language.implicitConversions
trait Par[A] {

  type Par[A] = ExecutorService => Future[A]

  def unit[A](a: => A): Par[A]

  def get[A](): A

  def sum(ints: IndexedSeq[Int]): Int = {
    if (ints.size <= 1) {
      ints.headOption.getOrElse(0)
    } else {
      val (l, r) = ints.splitAt(ints.size / 2)
      val parLeft: Par[Int] = unit(sum(l))
      val parRight: Par[Int] = unit(sum(r))
      //parLeft.get[Int]() + parRight.get[Int]()
      0
    }
  }
}


object Par {
  def main(args: Array[String]): Unit = {
    //s
    //println(parSome.sum(IndexedSeq(1,2,3,4)))
  }
}
