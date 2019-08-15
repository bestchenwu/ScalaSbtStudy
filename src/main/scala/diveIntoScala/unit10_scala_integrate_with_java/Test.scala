package diveIntoScala.unit10_scala_integrate_with_java

object Test {

    def add(x:Int,y:Int)=x+y

    def add2(x:List[Int]):Int={
        var sum = 0
        val iter = x.iterator
        while(iter.hasNext){
            sum+=iter.next()
        }
        sum
    }
}
