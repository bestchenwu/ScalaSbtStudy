package FunctionProgramming.moniod

object MoniodTest {

  def main(args: Array[String]): Unit = {
    val endoMoniod = Moniod.endoMoniod[Int]
    //print(endoMoniod.zero(13))
    print(endoMoniod.op((x:Int)=>x+3,(y:Int)=>y*2)(8))
  }
}
