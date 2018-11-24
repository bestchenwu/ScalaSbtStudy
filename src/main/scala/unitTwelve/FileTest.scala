package unitTwelve

import scala.io.Source

object FileTest extends App{

  //val flieName = "D:\\资料\\美囤妈妈\\BI\\babytree_git\\ScalaSbtStudy\\src\\main\\resources\\data.txt"
  val flieName="/data/problem/problem.txt"
  val bufferedSource = Source.fromFile(flieName)
  for(line <- bufferedSource.getLines()){
     println(line)
   }
  //bufferedSource.close()
}
