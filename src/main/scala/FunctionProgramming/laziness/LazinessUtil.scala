package FunctionProgramming.laziness

object LazinessUtil {

  def if2[A](cond:Boolean,onTrue: =>A,onFalse: =>A):A={
    if(cond){
      onTrue
    }else{
      onFalse
    }
  }
}
