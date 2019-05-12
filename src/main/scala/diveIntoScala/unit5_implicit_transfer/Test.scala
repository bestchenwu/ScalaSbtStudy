package diveIntoScala.unit5_implicit_transfer

object Test {

  def testSamePackage(): Unit = {
    println(x)
  }

  object Wildcard {
    def x = "wildcard import x"
  }

  def testWildCartImport(): Unit = {
    //通配导入
    import Wildcard._
    println(x)
  }

  object Explicit{
    def x = "Explicit import x"
  }

  def testExplicitImport(): Unit ={
    import Explicit.x
    import Wildcard._
    println(x)
  }

  def testLocalImport() ={
    val x = "testLocalImport"
    import Explicit.x
    import Wildcard._
    println(x)
  }


  def main(args: Array[String]): Unit = {
    //同包不同源
    testSamePackage()
    //通配导入
    testWildCartImport()
    //显式导入
    testExplicitImport()
    //本地导入
    testLocalImport()
  }
}
