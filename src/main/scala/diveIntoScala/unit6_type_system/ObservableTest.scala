package diveIntoScala.unit6_type_system

object ObservableTest {
  def main(args: Array[String]): Unit = {
//    val x = new IntStore(5)
//    val handler = x.observe(println)
//    x.set(2)
//    x.unobserve(handler)
      //摘除观察者后,set值不再触发打印函数
//    x.set(4)
    val callback = println(_:Any)
    val x = new IntStore(5)
    val y = new IntStore(3)
    val handle1 = x.observe(callback)
    val handle2 = y.observe(callback)
    //输出true,表明handle1和handle2完全相同
    println(handle1==handle2)
    //这里尝试移除会报错,说明路径依赖系统需要我们的类是同一个方法里生成的
    //y.unobserve(handle1)
  }
}
