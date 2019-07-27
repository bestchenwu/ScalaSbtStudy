package diveIntoScala.unit7_implicit_typeSystem

import HList._

sealed trait IndexedView {
  type Before <: HList //前置列表
  type After <: HList //后置列表
  type At //当前元素
  def fold[R](f: (Before, At, After) => R): R

  //删除当前元素
  def remove = fold((before, _, after) => before ::: after)

  def insertBefore[B](x: B) = fold { (before, current, after) => before :: x :: HCons(current, after) }

  //获得当前元素
  def get = fold((_, value, _) => value)
}

/**
  * 非递归的类
  *
  * @param list
  * @tparam H
  * @tparam T
  */
class HListView0[H,T<:HList](val list: H::T) extends IndexedView{
  override type Before = HNil
  override type After = T
  override type At = H

  override def fold[R](f: (Before, At, After) => R): R = f(HNil,list.head,list.tail)
}

/**
  * 递归的类<br/>
  * h是第一个元素,后面next是一个递归的视图
  *
  * @param h
  * @param next
  * @tparam H
  * @tparam NextIdView
  */
final class HListViewN[H,NextIdView<:IndexedView](h:H,next:NextIdView) extends  IndexedView{
  override type Before = H :: NextIdView#Before
  override type After = NextIdView#After
  override type At = NextIdView#At

  override def fold[R](f: (Before, At, After) => R): R = {
    next.fold((before,current,after)=>f(HCons(h,before),current,after))
  }
}
