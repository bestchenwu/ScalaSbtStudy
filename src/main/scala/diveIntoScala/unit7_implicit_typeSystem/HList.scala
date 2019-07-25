package diveIntoScala.unit7_implicit_typeSystem

sealed trait HList {


}

case class HCons[H,T<:HList](head:H,tail:T) extends HList{

  def ::[T] (v:T) = {
    HCons(v,this)
  }

  override def toString: String = head+","+tail
}

final class HNil extends HList {

  def ::[T] (v:T) = HCons(v,this)

  override def toString: String = "HNil"

}

object HList{

  //定义::类型 是方便我们在类型签名的时候使用它
  type :: [H,T<:HList] = HCons[H,T]
  //定义值类型是方便我们在表达式里使用它
  val :: = HCons
  val HNil = new HNil()

  def main(args: Array[String]): Unit = {
    //异构List
    ///每一个元素都是一个hcons,里面存储了类型和值
//    val x = "hello"::5::false::Nil
//    println(x)


  }

}
