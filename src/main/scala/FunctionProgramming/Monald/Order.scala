package FunctionProgramming.Monald

import FunctionProgramming.test.Gen


case class Order(item: Item, quantity: Int)

case class Item(name: String, price: Double)

object Order {
  def main(args: Array[String]): Unit = {
    val genOrder: Gen[Order] = for {
      name <- Gen.stringN(3)
      price <- Gen.uniform.map(_ * 10)
      quantity <- Gen.choose(0, 100)
    } yield Order(Item(name, price), quantity)
  }
}
