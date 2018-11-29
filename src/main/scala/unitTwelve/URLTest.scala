package unitTwelve
import java.io.File
import java.net.URL

import sys.process._
object URLTest extends App{
  //#> 表示重定向输出到某个位置
  new URL("http://www.baidu.com") #> new File("D:\\kumo\\output\\baidu.txt")
}
