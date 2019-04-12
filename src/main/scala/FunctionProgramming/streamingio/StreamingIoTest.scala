package FunctionProgramming.streamingio

import FunctionProgramming.io.IO

object StreamingIoTest {

  def main(args: Array[String]): Unit = {
    val result = linesGT4000("D:\\var\\www\\topic.txt")
    println(result.run)
  }

  def linesGT4000(fileName: String): IO[Boolean] = IO {
    val bufferedSource = scala.io.Source.fromFile(fileName)
    try {
      val lines = bufferedSource.getLines()
      var sum: Int = 0
      while (sum < 4000 && lines.hasNext) {
        sum += 1
        lines.next()
      }
      sum > 4000
    } finally {
      bufferedSource.close()
    }
  }

  def lines(fileName: String): IO[Stream[String]] = IO {
    val src = scala.io.Source.fromFile(fileName)
    //延迟IO,一旦从流中读取元素,它才会读取文件,而且只有在我们读到文件结尾才会关闭文件
    src.getLines().toStream.append {
      src.close;
      Stream.empty
    }
  }
}
