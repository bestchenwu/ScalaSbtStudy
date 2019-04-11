package FunctionProgramming.localeffects

object STTest {
  def main(args: Array[String]): Unit = {
    val st = ST(20)
    val newSt = st.map(_ * 2)
    print(newSt)
  }
}
