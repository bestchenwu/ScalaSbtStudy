package FunctionProgramming.test

import FunctionProgramming.state.RNG.SimpleRng
import FunctionProgramming.state.{RNG, State}

import scala.collection.mutable.ArrayBuffer


trait Prop {

  def check: Boolean

  def forAll[A](A: Gen[A])(f: A => Boolean): Prop = {
    null
  }

  def &&(p: Prop): Prop = new Prop {
    override def check: Boolean = {
      p.check == Prop.this.check
    }

    override def boolean: Gen[Boolean] = {
      null
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
    Gen(State(rng => RNG.nonNegativeInt(rng) match {
      case (n, rng2) => (n % (stopExcusive - start) + start, rng2)
    }))
  }

  def unit[A](f: => A): Gen[A] = {
    Gen(State(rng => (f, rng)))
  }

  //todo:这个函数的作用是什么
  def boolean: Gen[Boolean]

  /**
    * 基于生成器g,获取长度为N的列表
    *
    * @param n
    * @param g
    * @tparam A
    * @return
    */


  //todo:这里的sequence函数意思没有明白
  def listOfN1[A](n: Int, g: Gen[A]): Gen[List[A]] = {
    Gen(State.sequence(List.fill(n)(g.sample)))
  }
}

object Prop {
  //type是取一个别名
  type SuccessCount = Int
  type FailedCase = String

  def checkEither: Either[(FailedCase, SuccessCount), SuccessCount] = {
    null
  }

  def listOfN[A](n: Int, g: Gen[A]): Gen[List[A]] = {
    val list = ArrayBuffer[A]()
    val simpleRng = SimpleRng(45l)
    def loop(n: Int, g: Gen[A]): Gen[List[A]] = {
      if (n == 0) {
        Gen(State(rng => (list.toList, rng)))
      }else{
        list.append(g.sample.run(simpleRng)._1)
        loop(n-1,g)
      }
    }

    loop(n, g)
  }
}
