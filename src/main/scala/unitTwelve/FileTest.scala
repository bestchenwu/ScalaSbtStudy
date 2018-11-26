package unitTwelve

import java.io.{FileNotFoundException, IOException}

import scala.io.Source

object FileTest extends App {

  //val flieName = "D:\\资料\\美囤妈妈\\BI\\babytree_git\\ScalaSbtStudy\\src\\main\\resources\\data.txt"
  //  val flieName="/data/problem/problem.txt"
  //  val bufferedSource = Source.fromFile(flieName)
  //  for(line <- bufferedSource.getLines()){
  //     println(line)
  //   }
  //
  val fileName = "no-such.scala"
  try {
    var bufferedSource = Source.fromFile(fileName, "UTF-8")
  } catch {
    case e: FileNotFoundException => println("can't find file,for:" + e.getMessage)

    case e: IOException => println("IOException,for:" + e.getMessage)
  }
  //bufferedSource.close()
}
