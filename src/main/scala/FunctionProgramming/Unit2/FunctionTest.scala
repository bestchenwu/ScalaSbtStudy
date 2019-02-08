package FunctionProgramming.Unit2

import scala.annotation.tailrec

object FunctionTest {

  def factor(n: Int): Long = {
    //采用内部定义方法的方式
    @tailrec
    def factorize(n: Int, accumlator: Int): Long = {
      if (n <= 1) {
        accumlator
      } else {
        factorize(n - 1, n * accumlator)
      }
    }

    factorize(n, 1)

  }

  def fib(n: Int): Int = {
    val a = 0
    val b = 1

    @tailrec
    def loop(a: Int, b: Int, num: Int): Int = {
      if (num == 0) {
        a + b
      } else {
        loop(b, a + b, num - 1)
      }
    }

    loop(a, b, n - 3)
  }

  def main(args: Array[String]): Unit = {
    //    val result = factor(5)
    //    println(result)
    //0 1 1 2 3 5 8
    val result1 = fib(6)
    println(result1)
  }

}
