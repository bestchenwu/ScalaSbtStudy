package FunctionProgramming.test

import FunctionProgramming.state.{RNG, State}

case class Gen[+A](sample: State[RNG, A]) {
  val boolean: Gen[Boolean] =
    Gen(State(RNG.boolean))

  //val double:Gen[Double]=Gen(State(rng=>RNG.randomDouble(rng)))

  def flatMap[B](f: A => Gen[B]): Gen[B] = {
    Gen(sample.flatMap(a => f(a).sample))
  }

  def listOfN(size: Gen[Int]): Gen[List[A]] = {
    size.flatMap(n => Prop.listOfN(n, this))
  }

  def union[A](g1: Gen[A], g2: Gen[A]): Gen[A] = {
    boolean.flatMap(b => if (b) g1 else g2)
  }

  def weighted[A](g1: (Gen[A], Double), g2: (Gen[A], Double)): Gen[A] = {
      if(g1._2>g2._2) g1._1 else g2._1
  }


}

