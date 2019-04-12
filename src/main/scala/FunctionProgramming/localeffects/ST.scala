package FunctionProgramming.localeffects

sealed trait ST[S, A] {
  self =>
  protected def run(s: S): (A, S)

  def map[B](f: A => B): ST[S, B] = new ST[S, B] {
    override protected def run(s: S): (B, S) = {
      val (a, s1) = self.run(s)
      (f(a), s1)
    }
  }

  def flatMap[B](f: A => ST[S, B]): ST[S, B] = new ST[S, B] {
    override protected def run(s: S): (B, S) = {
      val (a, s1) = self.run(s)
      f(a).run(s1)
    }
  }

}

object ST {
  def apply[S, A](a: => A) = {
    lazy val memo = a
    new ST[S, A] {
      override protected def run(s: S): (A, S) = (memo, s)
    }
  }

  def runST[A](st: RunnableST[A]): A = st.apply[Unit].run(())._1


}

sealed trait STRef[S, A] {
  protected var cell: A

  def read: ST[S, A] = ST(cell)

  def write(a: A): ST[S, Unit] = new ST[S, Unit] {
    override protected def run(s: S): (Unit, S) = {
      cell = a
      ((), s)
    }
  }


}

object STRef {
  def apply[S, A](a: A): ST[S, STRef[S, A]] = ST(new STRef[S, A] {
    override protected var cell: A = a
  })
}

trait RunnableST[A] {
  def apply[S]: ST[S, A]
}

/**
  * Manifest用于存储运行时候A的信息<br/>
  * 例如def foo[T](x:List[T])(implict m:Manifest[T])
  * 这样当调用foo(List("a","b"),编译器就可以根据"a","b"推断出t类型是string
  * 这里可以简写为def foo[T:Manifest](x:List[T])
  *
  * @param ev$1
  * @tparam S
  * @tparam A
  */
sealed abstract class STArray[S, A: Manifest] {
  protected def value: Array[A]

  def size: ST[S, Int] = ST(value.size)

  def write(i: Int, a: A): ST[S, Unit] = new ST[S, Unit] {
    override protected def run(s: S): (Unit, S) = {
      value(i) = a
      ((), s)
    }
  }

  def read(i: Int): ST[S, A] = ST(value(i))

  def freeze: ST[S, List[A]] = ST(value.toList)

  def fill(xs: Map[Int, A]): ST[S, Unit] = new ST[S, Unit] {
    override protected def run(s: S): (Unit, S) = {
      for ((index, item) <- xs) {
        value(index) = item
      }
      ((), s)
    }
  }

  def swap[S](i: Int, j: Int): ST[S, Unit] = for {
    x <- read(i)
    y <- read(j)
    _ <- write(i, y)
    _ <- write(j, x)
  } yield ()

//  def partition[S](arr:STArray[S,Int],n:Int,r:Int,pivot:Int):ST[S,Int]={
//      val pivotValue = arr.read(pivot)
//      val j = n
//      for(i <- n to r){
//          if(arr.read(i))
//      }
//  }

}

object STArray {
  def apply[S, A: Manifest](size: Int, a: A): ST[S, STArray[S, A]] = ST(new STArray[S, A] {
    lazy val value = Array(a)
  })

  def fromList[S, A: Manifest](xs: List[A]): ST[S, STArray[S, A]] = ST(new STArray[S, A] {
    lazy val value = xs.toArray
  })
}

