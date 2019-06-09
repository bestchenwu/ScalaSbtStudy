package diveIntoScala.unit6_type_system

class A {

  //定义类型下界,表示接受List[Int]类型及List的父类
  type B >: List[Int]

  def foo(a: B) = a
}

class D {
  //定义了B类型的上界
  type B <: Traversable[Int]

  def sum(b: B) = b.foldLeft(0)(_ + _)
}

/**
  * scala中所有类型的最大上界是Any,最大下界是Nothing
  */
object TypeSystemTest {

  def main(args: Array[String]): Unit = {
    //    val a = new A {
    //      type B = Traversable[Int]
    //    }
    //由于这时候类型B变成了tranvserable 所以可以接受set类型
    //    val result = a.foo(Set[Int](1, 3, 5))
    //    println(result)
    //B类型不能声明为Set,因为不是List的父类
    //    val c = new A{
    //        type B = Set[Int]
    //    }

    val d = new D {
      type B = List[Int]
    }
    println(d.sum(List(1, 2, 3)))
    val d1 = new D {
      type B = Set[Int]
    }
    println(d1.sum(Set(4, 5, 6)))
  }
}
