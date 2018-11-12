package unitNighteen

trait Animal
class Dog(var name:String) extends Animal
class Cat(var name:String) extends Animal

object Humanish{
  trait HumanLike[A]{
    def speak(speaker:A):Unit
  }
  object HumanLike{
      implicit object DogIsHumanLike extends HumanLike[Dog]{
        override def speak(speaker: Dog): Unit = {
          println(s"I am a dog,name:${speaker.name}")
        }
      }

  }
}

object TypeClassDemo extends App{

    import Humanish.HumanLike
    //这里只有HumanLike类型的参数才可以使用speak方法
    def makeAnimalSpeak[A](animal:A)(implicit humanLike : HumanLike[A]): Unit ={
      humanLike.speak(animal)
    }

    //: => 这种称为by-name parameter函数，表示只有当函数参数blockCode真正被调用的时候才使用
    //可参见https://www.jianshu.com/p/f53e0b54a44a
    def timer[A](blockCode: => A )={
        val startTime = System.nanoTime()
        val result = blockCode
        val endTime = System.nanoTime();
        (result,endTime-startTime)
    }

    val (result,time) = timer({Thread.sleep(1);1})
    println(result)
    println(time)
  //makeAnimalSpeak(new Dog("dog"))
  //can't compile
  //makeAnimalSpeak(new Cat("cat"))
}
