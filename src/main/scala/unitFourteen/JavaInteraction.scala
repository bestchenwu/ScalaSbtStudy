package unitFourteen

import java.util

object JavaInteraction extends App{
  def nums = {
    val list = new util.ArrayList[String]();
    list.add("test1")
    list.add("test2")
    list
  }
  //这是jdk1.8的特性
  //nums.forEach(println(_));
  //nums.foreach(println(_))

}
