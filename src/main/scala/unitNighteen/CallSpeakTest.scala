package unitNighteen

class dog extends Animal {
   //scala中方法可以带上括号或者不带括号,
   // 但如果带上了括号,那么调用的时候也要带上括号
   def speak()={
     println("dog")
   }
}

class cat extends Animal{
  def speak()={
    println("cat");
  }
}

class superDog extends dog{
    override
    def speak()={
        println("superdog")
    }
}

object CallSpeakTest extends  App {
  //这里定义一个方法,参数A的上界是必须带有speak方法的类
  def speak[A <: {def speak():Unit}](obj:A)={
     obj.speak()
  }

  speak(new dog())
  speak(new cat())
}
