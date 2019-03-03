package FunctionProgramming.laziness

object LazinessUtilTest {

  def main(args: Array[String]): Unit = {
    LazinessUtil.if2(3>2,println("3>2"),print("error"))

  }
}
