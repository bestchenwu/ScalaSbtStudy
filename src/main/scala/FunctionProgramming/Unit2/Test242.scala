package FunctionProgramming.Unit2

import scala.util.control.Breaks._

object Test242 {

  def isSorted[A](array: Array[A], ordered: (A, A) => Boolean) = {
    val size = array.length
    var flag = true
    breakable {
      for (index <- 0 to size - 2) {
        if (!ordered(array(index), array(index + 1))) {
          flag = false
          break
        }
      }
    }
    flag
  }

  def partial[A, B, C](a: A, f: (A, B) => C): B => C = (b: B) => f(a, b)

  def partial1[A, B, C](f: (A, B) => C): A => (B => C) = a => b => f(a, b)

  def uncurry[A,B,C](f:A=>B=>C) :(A,B)=>C = (a:A,b:B)=>f(a)(b)

  def compose[A,B,C](f:B =>C,g:A =>B):A=>C = (a:A)=>f(g(a))

  def main(args: Array[String]): Unit = {
    //    val orderFunction = (x: Int, y: Int) => x < y
    //    val array = Array(1, 5, 13, 18)
    //    val result = isSorted(array, orderFunction)
    //    println(result)
    //    val f = (x: Double) => {var a=Math.PI / 2 - x;println(s"a=${a}");a}
    //    //相当于先调用f函数再调用andThen后面的函数
    //    val cos = f andThen (Math.sin)
    //    val result = cos(35)
    //    print(result)
    val f = (x: Int, y: Int) => x * y
    //      val partialFunction = partial(15,f)
    //      val result = partialFunction(8)
    //      println(result)
    val funtion2 = partial1(f)
    val result2 = funtion2(13)(15)
    println(result2)
    val f1 = (x:Int)=>x*2
    val g1 = (y:Int)=>y/3
    val result3 = compose(f1,g1)(18)
    println(result3)
  }
}
