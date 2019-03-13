package FunctionProgramming.parallelism

import java.util.concurrent._
import language.implicitConversions

trait Par[A] {

  /**
    * 定义一个转换算子(接受一个ExecutorService,并返回一个Future)
    *
    * @tparam A
    * @author chenwu on 2019.3.11
    */
  type Par[A] = ExecutorService => Future[A]

  /**
    * 创建一个即时结果为A的计算单元
    *
    * @param a
    * @tparam A
    * @return
    */
  def unit[A](a: => A): Par[A] = (es: ExecutorService) => UnitFuture(a)

  private case class UnitFuture[A](get: A) extends Future[A] {
    override def cancel(mayInterruptIfRunning: Boolean): Boolean = false

    override def isCancelled: Boolean = false

    override def isDone: Boolean = true

    override def get(timeout: Long, unit: TimeUnit): A = get
  }

  /**
    * 通过一个二元函数合并两个并行计算为一个并行计算
    *
    * @param a
    * @param b
    * @param f
    * @tparam A
    * @tparam B
    * @tparam C
    * @return
    */
  def map2[A, B, C](a: Par[A], b: Par[B])(f: (A, B) => C): Par[C] = (es: ExecutorService) => {
    val aValue = a(es)
    val bValue = b(es)
    UnitFuture(f(aValue.get(), bValue.get()))
  }

  /**
    * 对Par[A]的计算统一调用f函数得到B
    *
    * @param pa
    * @param f
    * @tparam A
    * @tparam B
    * @return
    */
  def map[A, B](pa: Par[A])(f: A => B): Par[B] = {
    map2(pa, unit())((a, _) => f(a))
  }

  /**
    * 将计算单元标记为在run时候进行并发求值
    *
    * @param a
    * @tparam A
    * @return
    */
  def fork[A](a: => Par[A]): Par[A] = (es: ExecutorService) => {
    es.submit(new Callable[A] {
      override def call(): A = {
        a(es).get()
      }
    })
  }

  /**
    * 将表达式包装为run时并发求值计算
    *
    * @param a
    * @tparam A
    * @return
    */
  def lazyUnit[A](a: => A): Par[A] = fork(unit(a))

  /**
    * 将函数f转换为异步运算
    *
    * @param f
    * @tparam A
    * @tparam B
    * @return
    */
  def asyncF[A, B](f: A => B): A => Par[B] = (a: A) => {
    lazyUnit(f(a))
  }

  /**
    * 对run标记的Par进行并发求值,并最终的返回结果
    *
    * @param a
    * @tparam A
    * @return
    */
  def run[A](s: ExecutorService)(a: Par[A]): Future[A] = a(s)


  def sortPar(parList: Par[List[Int]]): Par[List[Int]] = {
    map2(parList, unit())((a, _) => a.sorted)
  }

  def sequence[A](ps: List[Par[A]]): Par[List[A]] = {

    ps match {
      case Nil => unit(Nil)
      case h :: t => map2(h, fork(sequence(t)))(_ :: _)
    }
  }

  def parMap[A, B](ps: List[A])(f: A => B): Par[List[B]] = {
    val list: List[Par[B]] = ps.map(asyncF(f))
    sequence(list)
  }

  def parFilter[A](as: List[A])(f: A => Boolean): Par[List[A]] = {
    val list: List[Par[List[A]]] = as map (asyncF((a: A) => {
      if (f(a)) {
        List(a)
      } else {
        List()
      }
    }))
    //将list打散并合并
    map(sequence(list))(_.flatten)
  }

  /**
    * 查找队列里最大的元素
    *
    * @param seq
    * @param es
    * @return Int
    * @author chenwu on 2019.3.13
    */
  def findMax(seq: IndexedSeq[Int])(es: ExecutorService): Int = {

    def loop(seq: IndexedSeq[Int]): Int = {
      if (seq.size <= 1) {
        seq.headOption.getOrElse(0)
      } else {
        val halfLength: Int = seq.length / 2
        val (lSeq, rightSeq) = seq.splitAt(halfLength)
        val parLeft = unit(loop(lSeq))
        val parRight = unit(loop(rightSeq))
        val maxPar: Par[Int] = map2(parLeft, parRight)((x, y) => (x max y))
        run(es)(maxPar).get()
      }
    }

    loop(seq)
  }

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

case class ParImpl[A](es: ExecutorService) extends Par[A]

object Par {
  def main(args: Array[String]): Unit = {
    //s
    //println(parSome.sum(IndexedSeq(1,2,3,4)))
  }
}
