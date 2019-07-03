package diveIntoScala.unit6_type_system

/**
  * 型变分为不变、协变、逆变
  *
  * 不变记作[A],[B]
  * 协变的定义是如果B是A的子类型,那么T[B]也一定是T[A]的子类型
  *
  */
object VarianceTest {

  class T[+A] {}

  class P[-A] {}

  def foo(x: Any): String = s"hello x=${x}"

  def bar(y: String): Any = foo(y)

  trait Function[Arg, Return]

  trait Function1[Arg, +Return]

  trait List[+ItemType] {
    //由于这里类型参数是协变类型,所以在方法体声明的时候需要指明类型参数
    //def ++(other:List[ItemType]):List[ItemType]
    //def ++[ItemType](other:List[ItemType]):List[ItemType]
    //这里定义了类型约束 OtherItemType是ItemType的父类,所以List[OtherItemType]也一定是List[ItemType]的父类
    def ++[OtherItemType >: ItemType](other: List[OtherItemType]): List[OtherItemType]
  }

  class EmptyList[ItemType] extends List[ItemType] {
    def ++[OtherItemType](other: List[OtherItemType]) = other

    //override def ++[ItemType](other: List[ItemType]): List[ItemType] = other
  }

  def main(args: Array[String]): Unit = {
    //    val x = new T[AnyRef] //AnyRef是所有引用类型(String,Int等)的基类
    //    val x1 = new P[AnyRef]
    //    //因为AnyRef是Any的子类,那么T[AnyRef]一定是T[Any]的子类
    //    //所以这里相当于把子类强行转换为父类
    //    val y: T[Any] = x //Any是所有类中最顶层的
    //    //这里是向下转型
    //    //val z:T[String] = x
    //    //这里由于P类型是逆变的,String类型是AnyRef的子类,所以P[String]是P[AnyRef]的父类
    //    val y1: P[String] = x1
    //
    //    println(foo(333))
    //    println(bar("test2"))

    //        val x = new Function[String, Any] {}
    //        val x1 = new Function1[String, String] {}
    //        val y1: Function1[String, Any] = x1
    //val x1:Function[Any,String] = x

    //    val strings = new EmptyList[String]
    //    val ints = new EmptyList[Int]
    //    val anys = new EmptyList[Any]
    //    val anyrefs = new EmptyList[AnyRef]
    //    //这里编译器做了类型推断,将Any作为最终的类型
    //    println(strings ++ ints)
    //    println(strings ++ anys)
    //    println(strings ++ anyrefs)
    //这里的list类型是util.List[_]
    //scala在这里用一个存在类型_ 来用于与java的泛型兼容

    val list = ExistedTypeTest.makeList()
    //但是不可以向list里面添加元素,除非scala确定添加的元素类型和list的类型是同一种
    //list.add("haha")
  }
}
