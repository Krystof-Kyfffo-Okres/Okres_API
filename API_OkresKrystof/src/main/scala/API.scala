import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest}
import akka.http.scaladsl.unmarshalling.Unmarshal

import java.net.URLEncoder
import scala.io.StdIn.readLine

object API extends App{
  implicit val as = ActorSystem()
  implicit val ec = as.dispatcher

  implicit class MapToJson[V](params: Map[String, V]) {
    def toUrlParams: String = params.map { case (k, v) => s"$k=$v" }.mkString("&")
  }

  val search = readLine("Enter name of a card: ")


  val request = HttpRequest(
    method = HttpMethods.GET,
    uri = s"https://omgvamp-hearthstone-v1.p.rapidapi.com/cards/search/$search",
    headers = Seq(
      RawHeader("X-RapidAPI-Key", "f6f990a8ffmsh92f3750c4cc5759p10ce73jsn738bfe8683f4"),
      RawHeader("X-RapidAPI-Host", "omgvamp-hearthstone-v1.p.rapidapi.com")
    ),
    //entity = HttpEntity(ContentTypes.application/json, requestBody)
  )

  val performRequestFut = for {
    response <- Http().singleRequest(request)
    status = response.status
    body <- Unmarshal(response.entity).to[String]
    _ = response.entity.discardBytes()
  } yield println(s"status: $status, body: $body")
  performRequestFut.andThen(_ => as.terminate()) // we do not need Await.result because of as running
}
