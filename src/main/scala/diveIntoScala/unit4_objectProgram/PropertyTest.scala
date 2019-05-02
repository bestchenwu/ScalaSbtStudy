package diveIntoScala.unit4_objectProgram

object PropertyTest {

  def main(args: Array[String]): Unit = {
    val x = new Property {
      override lazy val name: String = "haha"
    }
    println(x)
  }
}
