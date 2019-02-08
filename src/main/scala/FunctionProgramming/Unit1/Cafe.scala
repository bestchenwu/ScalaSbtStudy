package FunctionProgramming.Unit1

import java.util.concurrent.atomic.AtomicInteger

class CreditCard {

  //支付费用
  def charge(price: Float) = {

  }
}

object Coffee {
  val DEFAULT_COFFEE_PRICE = 33.5f
  var coffee_id = new AtomicInteger(1);
}

class Coffee(var price: Float) {

  var current_coffee_id = 0

  def this() {
    this(Coffee.DEFAULT_COFFEE_PRICE)
  }

  def setId()={
    current_coffee_id  = Coffee.coffee_id.getAndAdd(1)
  }

  override def toString: String = {
    s"Coffee[coffee_id=${current_coffee_id},price=${price}]"
  }
}

class Charge(var creditCard: CreditCard, var price: Float) {

  def combine(other: Charge) = {
    if (creditCard == other.creditCard) {
      //说明属于同一张信用卡,可以合并支付
      creditCard.charge(price + other.price)
      this
    } else {
      throw new IllegalStateException("can't use different creditCard")
    }
  }
}

class Cafe {
  //以下属于有副作用的方法
  //private val creditCard: CreditCard = new CreditCard()
  //  def buyCoffee() = {
  //    val coffee: Coffee = new Coffee(35.8f)
  //    creditCard.charge(coffee.price)
  //    coffee
  //  }

  //以下方法属于函数式编程,无副作用
  def buyCoffee(creditCard: CreditCard): (Coffee, Charge) = {
    val coffee: Coffee = new Coffee(33.2f)
    coffee.setId()
    (coffee, new Charge(creditCard, coffee.price))
  }

  def buyCoffees(creditCard: CreditCard, coffeeNumber: Int) = {
    val purchase: List[(Coffee, Charge)] = List.fill(coffeeNumber)(buyCoffee(creditCard))
    val (coffees, charges) = purchase.unzip
    (coffees, charges.reduce((charge1, charge2) => charge1.combine(charge2)))
  }
}
