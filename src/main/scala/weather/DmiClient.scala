package weather

import sttp.client._

class DmiClient(
    apiKey: Option[String],
    implicit val backend: SttpBackend[
      Identity,
      Nothing,
      okhttp.WebSocketHandler
    ],
    serviceVersion: String = "v2"
) {
  assert(apiKey.isDefined, "API key must be defines as an environment variable")
  val serverName = "https://dmigw.govcloud.dk"
  
  def getClimateData(
      params: Map[String, Option[String]]
  ): ujson.Value = {

    val response = getResponse(
      getBaseRequest(serviceVersion, "climateData", "stationValue"),
      params
    )
    return Response.read(response)
  }

  def getMetObsData(
      params: Map[String, Option[String]]
  ): ujson.Value = {

    val response = getResponse(
      getBaseRequest(serviceVersion, "metOps", "observation"),
      params
    )
    return Response.read(response)
  }

  private def getResponse(
      baseRequest: String,
      params: Map[String, Option[String]]
  ): Identity[Response[Either[String,String]]] = {
    
    val uri = uri"$baseRequest?$params&api-key=$apiKey"
    val request = basicRequest.get(uri)
    return request.send()
  }

  private def getBaseRequest(
      serviceVersion: String,
      serviceName: String,
      collection: String
  ): String = {
    return s"$serverName/$serviceVersion/$serviceName/collections/$collection/items"
  }
}
