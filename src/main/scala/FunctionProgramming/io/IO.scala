package FunctionProgramming.io

import FunctionProgramming.Monald.Monoad

sealed trait IO[A] {
  //self指向IO对象本身
  self =>
  def run: A

  def map[B](f: A => B): IO[B] = new IO[B] {
    override def run = {
      f(self.run)
    }
  }

  def flatMap[B](f: A => IO[B]): IO[B] = new IO[B] {
    override def run = {
      f(self.run).run
    }
  }

  //  def ++(io: IO): IO = new IO {
  //    override def run: Unit = {
  //      //self是对外部IO对象的引用
  //      self.run;
  //      io.run
  //    }
  //  }
}

object IO extends Monoad[IO] {
  override def unit[A](a: => A): IO[A] = new IO[A] {
    override def run: A = a
  }

  override def flatMap[A, B](fa: IO[A])(f: A => IO[B]): IO[B] = fa flatMap f

  def apply[A](a: => A): IO[A] = unit(a)

  def ReadLine: IO[String] = IO(scala.io.StdIn.readLine)
}
