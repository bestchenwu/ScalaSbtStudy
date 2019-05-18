package diveIntoScala.unit5_implicit_transfer

package object foo {
  //这个例子说明可以用一个包对象来存放各种隐式绑定
  implicit def newFoo = new Foo()
}
package foo {

  class Foo {
    override def toString: String = "I am a foo"
  }

}

class ImplicitPackageTest {

}
