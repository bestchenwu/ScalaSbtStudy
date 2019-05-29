//package diveIntoScala.unit3_specifies
//
//import scala.annotation.switch
//
//object Average {
//
//  /**
//    * 在查看该方法编译后的代码,可以看到:
//    * Average.class 'Average$.class' 'Average$$anonfun$1.class'
//    * 'Average$.class'表示单例对象,这样静态方法的调用都会从Average.class发给该单例对象
//    * 'Average$$anonfun$1.class'表示方法体里面的匿名函数
//    * 以上对象都可以通过javap -p xx.class来查看
//    * javap -l 用于查看本地变量声明,javap -p 用于查看类和所有成员 javap -c 用于获取反编译的源代码
//    *
//    * @param doubles
//    * @return
//    */
//  def averageDoubleList(doubles: List[Double]) = {
//    val sum = doubles.foldRight(0.0)(_ + _)
//    sum / doubles.size.toDouble
//  }
//
//  /**
//    * 如果加上case y:Int,则编译器会提示could not emit switch,因为触犯了第四条
//    * 原因是要转换为tableSwitch,必须满足以下几个条件:
//    * 1、匹配的值必须是已知整数 ,不能是某个变量,比如这里不能将val two = 2,然后用two来匹配
//    * 2、case语句里不能包含if 等条件缘聚
//    * 3、case不能超过2个(其中第二个),注明这一条在2.11+版本里取消了
//    * 4、编译的时候表达式必须有值 类似于case y:Int就通不过哦
//    * 如果通过了编译,则会形成tableswitch{
//    *    1:""
//    *    2:""
//    *    3:""
//    * }
//    *
//    * @param x
//    * @return
//    */
//  def notPromised3(x: Int) = {
//    (x: @switch) match {
//      case 1 => "one"
//      case 2 => "two"
//      //case 3 => "three"
//      //case y: Int => s"x=${y}"
//    }
//  }
//
//  class `$anonfun` {
//
//    class `1` {
//      println("oh my god")
//    }
//
//  }
//
//}
//
//class `Average$$anonfun$1.class` {
//  println("oh my god")
//}