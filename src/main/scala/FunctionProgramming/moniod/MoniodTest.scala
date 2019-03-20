package FunctionProgramming.moniod

object MoniodTest {

  def main(args: Array[String]): Unit = {
    val endoMoniod = Moniod.endoMoniod[Int](15)
    print(endoMoniod.zero)
  }
}
