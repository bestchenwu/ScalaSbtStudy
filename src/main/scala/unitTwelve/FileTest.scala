package unitTwelve

import java.io.{File, FileNotFoundException, IOException}

import scala.io.Source

object FileTest extends App {

  //val fileName = "D:\\资料\\美囤妈妈\\BI\\babytree_git\\ScalaSbtStudy\\src\\main\\resources\\data.txt"
  //val fileName = "/data/problem/problem.txt"
  //val bufferedSource = Source.fromFile(fileName)
  //也可以用字符串伪装成文件
  val fileName = "test1\ntest2\n"
  val bufferedSource = Source.fromString(fileName)
  val newLine:Char = '\n'.toChar
  var lineCount = 0
  //这种语法称之为for循环的过滤表达式,或者称为卫语句
  for{
      line <- bufferedSource.getLines()
      char <- line
      if(char.equals(newLine))
  } lineCount+=1
  println(lineCount)
//  for (line <- bufferedSource.getLines()) {
//    println(line)
//  }
  //默认使用的就是char的循环器,如果文件很大，则处理速度相当慢
//  for(char <- bufferedSource){
//     println(char)
//  }

  //  val fileName = "no-such.scala"
  //  try {
  //    var bufferedSource = Source.fromFile(fileName, "UTF-8")
  //  } catch {
  //    case e: FileNotFoundException => println("can't find file,for:" + e.getMessage)
  //
  //    case e: IOException => println("IOException,for:" + e.getMessage)
  //  }
  //bufferedSource.close()
}
