package unitNighteen

import scala.collection.mutable.ArrayBuffer

//trait Animal {
//  def speak
//}

//class DogFamily[x <: dog] extends ArrayBuffer[x]

//object VariableCollectionTest extends App {
//
//  //ArrayBuffer是可变集合，但是其中的元素类型不可变
//  //因为ArrayBuffer的定义是ArrayBuffer[T],如果试图传入ArrayBuffer[superDog] 会编译失败
//  def makeAllDogsSpeak(u: ArrayBuffer[dog]): Unit = {
//    u.foreach(dogElem => dogElem.speak())
//  }
//
//  //但是List虽然是不可变集合，但List的元素类型是可变的，因为传入的是List[+T]
//  def makeAllDogsSpeak2(u: List[dog]): Unit = {
//    u.foreach(dogElem => dogElem.speak())
//  }
//
//
//
//  //    var arrayBuffer = ArrayBuffer[dog]()
//  //    val dog1 = new dog()
//  //    val dog2 = new superDog()
//  //    arrayBuffer+=dog1
//  //    arrayBuffer+=dog2
//  //    makeAllDogsSpeak(arrayBuffer)
//
////  val dog1 = new superDog()
////  val dog2 = new superDog()
////  var arrayBuffer = List[superDog](dog1, dog2)
////  makeAllDogsSpeak2(arrayBuffer)
////
////  val dogFamily = new DogFamily[dog]()
////  val dog1 = new dog()
////  val dog2 = new superDog()
////  dogFamily+=dog1
////  dogFamily+=dog2
////  makeAllDogsSpeak(dogFamily)
//
//  def add[A](x:A,y:A)(implicit numeric: Numeric[A])= numeric.plus(x,y)
//  val result = add(2,3)
//  println(result)
//  //由于上面是类型类的作用方式，只能接受Numeric类型的参数
//  //更多类型类的例子可参见
//  //val result1 = add("test","haha")
//
//
//}
