package FunctionProgramming.test

import java.util.Random

import FunctionProgramming.state.{RNG, SimpleRng, State}


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
    val simpleRNG = SimpleRng(47l)
    //Gen(State(simpleRNG.nonNegativeInt(simpleRNG)._2.))
    Gen(State(simpleRNG=>(10,simpleRNG)))
    null
        //Gen(State(RNG.nonNegativeInt).map(n => start + n % (stopExclusive-start)))
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
