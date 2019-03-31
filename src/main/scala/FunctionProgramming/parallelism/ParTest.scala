package FunctionProgramming.parallelism

import java.util.concurrent.Executors

object ParTest {

  def main(args: Array[String]): Unit = {
    val es = Executors.newFixedThreadPool(1)
    //    //      val parList = par.parFilter(List(1,5,2,8,9))((x:Int)=>x%2==0)
    //    //      val resultList = par.run(es)(parList).get()
    //    //      print(resultList)
    //    val maxValue = par.findMax(IndexedSeq(3, 9, 8, 2, 14, 5))(es)
    //    print(maxValue)
    val a = Par.lazyUnit(42 + 1)
    //println(par.equal(a, par.fork(a))(es))
  }
}
