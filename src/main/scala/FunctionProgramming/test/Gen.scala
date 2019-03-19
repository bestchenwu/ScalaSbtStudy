package FunctionProgramming.test

import FunctionProgramming.state.{RNG, State}

case class Gen[A](sample: State[RNG, A]) {

}
