package FunctionProgramming.streamingio

/**
  * 转换I类型的流为O的流
  *
  * @tparam I
  * @tparam O
  */
sealed trait Process[I, O] {

  def apply(s: Stream[I]): Stream[O] = this match {
    case Halt() => Stream()
    case Await(recv) => s match {
      //这里h代表一个元素,t代表一个尾部Stream
      case h #:: t => recv(Some(h))(t)
      case xs => recv(None)(xs)
    }
    case Emit(head, tail) => head #:: tail(s)
  }

  def repeated: Process[I, O] = {

    def go(process: Process[I, O]): Process[I, O] = {
      process match {
        case Emit(head, tail) => Emit(head, go(tail))
        //当遇到结束时候,重启自己
        case Halt() => go(this)
        case Await(recv) => Await {
          case Some(i) => go(recv(Some(i)))
          case None => Halt()
        }
      }
    }

    go(this)
  }


}

object Process {

  def liftOne[I, O](f: I => O): Process[I, O] = Await {
    case Some(i) => Emit(f(i))
    case None => Halt()
  }

  def filter[I](p: I => Boolean): Process[I, I] = Await[I, I] {
    case Some(i) if (p(i)) => Emit(i)
    case _ => Halt()
  }.repeated

  def sum: Process[Double, Double] = {

    def loop(acc: Double): Process[Double, Double] = Await {
      case Some(newDouble) => Emit(acc + newDouble, loop(acc + newDouble))
      case None => Halt()
    }

    loop(0.0)
  }

  def take[I](n: Int): Process[I, I] = {

    def loop(n: Int): Process[I, I] = Await {
      case Some(i) if (n > 0) => Emit(i, loop(n - 1))
      case _ => Halt()
    }

    loop(n)
  }

  def drop[I](n: Int): Process[I, I] = {
    def loop(n: Int): Process[I, I] = Await[I, I] {
      case Some(i) if (n == 0) => Emit(i)
      case _ => {
        loop(n - 1)
      }
    }.repeated

    loop(n)
  }

 // def takeWhile[I]()
}

/**
  * 告诉驱动器将head值递送给输出流,而后tail的部分继续由状态机处理
  *
  * @param head
  * @param tail
  * @tparam I
  * @tparam O
  */
case class Emit[I, O](head: O, tail: Process[I, O] = Halt[I, O]()) extends Process[I, O]

/**
  * 请求从输入流得到下一个值,驱动器则将下一个值传递给函数recv,一旦输入流再无元素则给None
  *
  * @param recv
  * @tparam I
  * @tparam O
  */
case class Await[I, O](recv: Option[I] => Process[I, O]) extends Process[I, O]

/**
  * 暂时没有任何元素要从输入流读取,或递送给输出流
  *
  * @tparam I
  * @tparam O
  */
case class Halt[I, O]() extends Process[I, O]
