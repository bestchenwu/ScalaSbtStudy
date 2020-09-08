package akkaStudy.unit5_parallize

import org.scalatest.funspec.AnyFunSpecLike
import org.scalatest.matchers.must.Matchers

import scala.concurrent.{Await, Future, Promise}
import concurrent.ExecutionContext.Implicits.global
import scala.util.Success
import scala.concurrent.duration._

case class TaxCut(cut: Int)

case class LameExcuse(msg: String) extends Exception(msg)
object Govement {

  def redeemCampaignPledge2(): Future[TaxCut] = {
    val p = Promise[TaxCut]()
    Future {
      println("Starting the new legislative period.")
      Thread.sleep(200)
      p.failure(LameExcuse("global economy crisis"))
      println("We didn't fulfill our promises, but surely they'll understand.")
    }
    p.future
  }

  def redeemCampaignPledge(): Future[TaxCut] = {
    val p = Promise[TaxCut]()
    //使用apply方法，默认会使用promise。
    Future {
      println("Starting the new legislative period.")
      Thread.sleep(200)
      p.success(TaxCut(20))
      println("We reduced the taxes! You must reelect us!!!!1111")
    }
    p.future
  }
}

class PromiseTest extends AnyFunSpecLike with Matchers {

  describe("redeemCampaignPledge success") {
    it("we should get TaxCut 20") {
      //val future = Govement.redeemCampaignPledge()
      val future = Govement.redeemCampaignPledge2()
      val eventualCut = Await.ready(future, 1000 milliseconds)
      val taxCutFuture = eventualCut.mapTo[TaxCut]
      taxCutFuture.onComplete {
        case Success(TaxCut(value)) => println(s"success get taxCount =  $value")
        case scala.util.Failure(exception) => println(s"broke the tax by ${exception.getMessage}")
      }
      //      future.onComplete {

      //      }
    }
  }
}
