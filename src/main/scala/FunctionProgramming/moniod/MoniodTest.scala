package FunctionProgramming.moniod

object MoniodTest {

  def main(args: Array[String]): Unit = {
//    val endoMoniod = Moniod.endoMoniod[Int]
//    //print(endoMoniod.zero(13))
//    print(endoMoniod.op((x:Int)=>x+3,(y:Int)=>y*2)(8))
//    val list = List(1,2,3)
//    val result = Moniod.foldMap(list,Moniod.intAddition)((x:Int)=>x*2)
//    print(result)
//    val seq = IndexedSeq("a","b","c","e","d","f")
//    val result = Moniod.foldMapV(seq,Moniod.StringMoniod)((x:String)=>x)
//    print(result)
    //测试单词计数
//    val wc1 = Part("lorem",1,"do")
//    val wc2 = Part("lor",2,"")
//    val wc3 = Moniod.wcMonoid.op(wc1,wc2)
//    print(wc3)
//    val str = " lorem haha jack   sweet"
//    println(Moniod.countWordsByFoldMap(str))

    val vector = Vector("a","rose","is","a","rose")
    println(Moniod.bag(vector))
  }
}
