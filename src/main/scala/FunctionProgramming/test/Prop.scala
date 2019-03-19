package FunctionProgramming.test

import java.util.Random

import FunctionProgramming.state.{RNG, State}


trait Prop {

  def check: Boolean

  def forAll[A](A: Gen[A])(f: A => Boolean): Prop = {
    null
  }

  def &&(p: Prop): Prop = new Prop {
    override def check: Boolean = {
      p.check == Prop.this.check
    }
  }

  /**
    * 生成指定范围内的整数(不含有右边界值)
    *
    * @param start
    * @param stopExcusive
    * @return Gen
    * @author chenwu on 2019.3.18
    */
  def choose(start: Int, stopExcusive: Int): Gen[Int] = {

    //answer1
    //Gen(State(RNG.nonNegativeInt).map(n=>n%stopExcusive-start+start))

    //answer2
    Gen(State(rng=>RNG.nonNegativeInt(rng) match{
      case (n,rng2)=>(n%(stopExcusive-start)+start,rng2)
    }))
  }

  def unit[A](f: =>A):Gen[A] = {
      Gen(State(rng=>(f,rng)))
  }

}

class SimpleProp extends Prop{
  override def check: Boolean = {
    true
  }
}

case class Gen[A](sample: State[RNG, A]) {


}

object Prop {
  //type是取一个别名
  type SuccessCount = Int
  type FailedCase = String

  def checkEither: Either[(FailedCase, SuccessCount), SuccessCount] = {
    null
  }
}
