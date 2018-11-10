package unitNighteen

class Queue[+T](leading: List[T], tailing: List[T]) {
  //表明U是T的上界,U必须是T的超类或者T本身
  def append[U >: T](x: U): Queue[U] = {
    new Queue(leading, x :: tailing)
  }

//  def appendLower[V <: T](x:V)={
//    tailing.:+(x)
//  }

  def printLeading(): Unit = {
    leading.foreach(println);
  }

  def printTailing() = {
    tailing.foreach(println);
  }

}

object Queue extends App {

  val queue = new Queue(List(1), List(2));
  val queue1 = queue.append(3)
  //queue1.printLeading()
  queue1.printTailing()
}
