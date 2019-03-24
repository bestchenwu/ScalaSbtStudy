package FunctionProgramming.moniod

import FunctionProgramming.dataStructure.{Branch, Leaf}

object FoldableTest {

  def main(args: Array[String]): Unit = {
      val tree = Branch[String](Leaf("a"),Leaf("b"),"c")
//      //val result = Foldable.foldTree.foldLeft(tree)("d")((B:String,A:String)=>A+B)
//      val result = Foldable.foldTree.foldRight(tree)("d")((B:String,A:String)=>A+B)
//      println(result)//输出abcd
    val list:List[String] = Foldable.foldTree.toList(tree)
    print(list)
    //Foldable.foldableList.toList(List(1,2,3,4))
  }
}
