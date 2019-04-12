package FunctionProgramming.localeffects

object STTest {
  def main(args: Array[String]): Unit = {
    //    val st = ST(20)
    //    val newSt = st.map(_ * 2)
    //    print(newSt)
    //    val result = new RunnableST[(Int, Int)] {
    //      override def apply[Int] = {
    //        for {
    //          r1 <- STRef(2)
    //          r2 <- STRef(5)
    //          a <- r1.read
    //          b <- r2.read
    //          _ <- r1.write(b * 2)
    //          _ <- r2.write(a * 2)
    //          a <- r1.read
    //          b <- r2.read
    //        } yield (a, b)
    //      }
    //    }
    //
    //    val result1 = ST.runST(result)
    //    println(result1)
    //val stArray = STArray(size=5,a=8)
    //ST.runST(new RunnableST[STArray[S, A]] {})
    //    val stArray = new STArray[Int, Int] {
    //      override protected def value: Array[Int] = Array(1, 2, 3)
    //    }
    val stArray = STArray.fromList(List("a", "c", "b"))
//    ST.runST(new RunnableST[STArray[String, String]] {
//      override def apply[S]: ST[S, STArray[String, String]] = STArray.fromList(List("a", "c", "b"))
//    })
  }
}
