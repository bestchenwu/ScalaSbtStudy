package FunctionProgramming.test

import FunctionProgramming.state.RNG.SimpleRng
import FunctionProgramming.state.{RNG, State}

object GenTest {

  def main(args: Array[String]): Unit = {
    //    val intList = Gen.listOf(Gen.choose(0,100))
    //    val prop = forAll(intList)(ns=>ns.reverse.reverse == ns)&&forAll(intList)(ns=>ns.headOption==ns.reverse.lastOption)
    //    val failProp = forAll(intList)(ns=>ns==ns.reverse)
    //    println(prop.check())
    //    println(failProp.check())
    //Gen.listOf()
    val simpleProp = new SimpleProp()
    val rng = SimpleRng(45l)
    val a: Gen[Int] = simpleProp.choose(23, 50)
    println(a.sample.run(rng))
    //    def func(a:Int):Gen[Double]={
    //      val genDouble:Gen[Double] = simpleProp.unit(a.toDouble/100)
    //      genDouble
    //    }
    //    val b:Gen[Double] = a.flatMap(func)
    //    println(b.sample.run(rng))
    val aGen: Gen[Double] = Gen(State(rng => RNG.randomDouble(rng)))
    val bGen: Gen[List[Double]] = aGen.listOfN(a)
    print(bGen.sample.run(rng))
  }
}
