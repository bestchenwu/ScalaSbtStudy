package FunctionProgramming.Unit2

object MyModuleTest {

   private def abs(n:Int)={
     if(n>0){
       n
     }else{
       -n
     }
  }

  private def formatResult(n:Int,f:Int=>Int)={
      val message = "prime number is %d,format result is %d"
      message.format(n,f(n))
  }

  def main(args: Array[String]): Unit = {
    println(formatResult(-5,abs))
  }
}
