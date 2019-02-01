package FunctionProgramming.Unit1

class CreditCard {

  //支付费用
  def charge(price: Float) = {

  }
}

class Coffee(var price: Float) {
}

class Cafe {

  private val creditCard: CreditCard = new CreditCard()

  def buyCoffee() = {
    val coffee: Coffee = new Coffee(35.8f)
    creditCard.charge(coffee.price)
    coffee
  }
}
