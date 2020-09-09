package akkaStudy

import akka.util.Timeout
import scala.concurrent.duration._

package object unit7_mailbox {

  implicit val timeout: Timeout = Timeout(5 seconds)
}
