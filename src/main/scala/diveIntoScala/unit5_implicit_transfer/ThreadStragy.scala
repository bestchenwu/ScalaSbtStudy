package diveIntoScala.unit5_implicit_transfer

import java.util.concurrent.{Callable, ExecutorService, Executors}

/**
  * 定义线程执行策略
  *
  * @author chenwu on 2019.5.26
  */
trait ThreadStragy {

  def exectue[A](func0: Function0[A]): A
}

/**
  * 单线程执行策略
  *
  * @author chenwu on 2019.5.26
  */
object SingleThreadStragy extends ThreadStragy {
  override def exectue[A](func0: () => A): A = func0()
}

/**
  * 多线程策略
  */
object MultiThreadStragy extends ThreadStragy {

  val threadPool = Executors.newFixedThreadPool(10)

  override def exectue[A](func0: () => A): A = {
    val future = threadPool.submit(new Callable[A] {
      override def call(): A = {
        func0()
      }
    })
    future.get()
  }
}
