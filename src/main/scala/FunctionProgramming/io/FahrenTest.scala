package FunctionProgramming.io

object FahrenTest {

  def fahrenToCelsius(f:Double):Double = (f-32)*5.0/9.0

  def converter:Unit = {
    println("Enter a temperature:")
    val d= scala.io.StdIn.readLine().toDouble
    println(fahrenToCelsius(d))
  }

  def main(args: Array[String]): Unit = {
    //converter
    //IO.ReadLine.map(_.toDouble)
    val p = IO.forever(IO.PrintLine("still going"))
  }
}
