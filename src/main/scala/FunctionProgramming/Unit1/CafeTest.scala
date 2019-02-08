package FunctionProgramming.Unit1

object CafeTest {

  def main(args: Array[String]): Unit = {
    val creditCard = new CreditCard()
    val cafe = new Cafe()
    val result = cafe.buyCoffees(creditCard,3)
    val coffees = result._1
    println(coffees)
  }
}
