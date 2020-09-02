package akkaStudy.unit3_message_transfer

import akka.actor.Actor
import akka.actor.Status.Failure
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import de.l3s.boilerpipe.sax.HTMLFetcher
import org.apache.http.HttpResponse

import java.net.URL
/**
  * 根据一个url去解析内容
  *
  * @author chenwu on 2020.9.2
  */
class HttpClientActor extends Actor {

  val httpClient = HttpClients.createDefault()

  override def receive: Receive = {
    case url: String => {
      val get = new HttpGet(url)
      try {
//        val response = httpClient.execute(get)
//        val entity = EntityUtils.toString(response.getEntity)

        val htmlDoc = HTMLFetcher.fetch(new URL(url))
        //sender() ! HttpResponse(entity)
        sender() ! HttpResponse(String.valueOf(htmlDoc.getData))
      } catch {
        case e: Exception => sender() ! Failure(e)
      }

    }
    case other => sender() ! Failure(new IllegalArgumentException("unrecoginzed message:" + other))
  }
}
