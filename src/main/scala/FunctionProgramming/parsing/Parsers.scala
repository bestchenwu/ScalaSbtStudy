package FunctionProgramming.parsing

import java.util.regex._
import scala.util.matching.Regex
import language.higherKinds
import language.implicitConversions

//trait Parsers [Parser[+_]] { self =>
//
//  //type Parser[+A] = ParseState => Result[A]
//
//  /** `isSliced` indicates if the current parser is surround by a
//    * `slice` combinator. This lets us avoid building up values that
//    * will end up getting thrown away.
//    *
//    * There are several convenience functions on `ParseState` to make
//    * implementing some of the combinators easier.
//    */
////  case class ParseState(loc: Location, isSliced: Boolean) {
////    // some convenience functions
////    def advanceBy(numChars: Int): ParseState =
////      copy(loc = loc.copy(offset = loc.offset + numChars))
////    def input: String = loc.input.substring(loc.offset)
////    def unslice = copy(isSliced = false)
////    def reslice(s: ParseState) = copy(isSliced = s.isSliced)
////    def slice(n: Int) = loc.input.substring(loc.offset, loc.offset + n)
////  }
////
////  def run[A](p: Parser[A])(input: String): Either[ParseError,A]
//
//  implicit def string(s: String): Parser[String]
//  implicit def operators[A](p: Parser[A]) = ParserOps[A](p)
//  implicit def asStringParser[A](a: A)(implicit f: A => Parser[String]):
//  ParserOps[String] = ParserOps(f(a))
//
//  def char(c: Char): Parser[Char] =
//    string(c.toString) map (_.charAt(0))
//}
