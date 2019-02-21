package FunctionProgramming.dataStructure

object TreeTest {

  def main(args: Array[String]): Unit = {
    //      val tree = new Branch[String](left = new Branch[String](node="a",left=new Leaf[String]("b"),right=new Leaf[String](value = "c")),right=null,node="root")
    //      val treeNodeCount = Tree.size(tree)
    //      print(treeNodeCount)
    val tree = new Branch[Int](left = new Branch[Int](node = 3, left = new Leaf[Int](5), right = new Branch[Int](new Leaf(3),new Leaf(22),5)), right = null, node = 2)
//    val maxNodeValue = Tree.maximum(tree)
//    print(maxNodeValue)
//    val treeLength = Tree.depth(tree)
//    print(treeLength)
    val newTree = Tree.map(tree)((x:Int)=>x*2)
    print(newTree)
  }
}
