package diveIntoScala.unit4_objectProgram

object DataAccessTest {

  def main(args: Array[String]): Unit = {
    //    val x = new DataAccess {
    //
    //    }
    //    x.query("haha")
    //这里使用了with 语法来混入特质 来实现多重继承 以最后一个特质的实现为准
    val service = new DataAccess()
    service.query("haha")
  }
}
