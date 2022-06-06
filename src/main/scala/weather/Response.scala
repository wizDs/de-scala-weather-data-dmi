package weather

import sttp.client._
import scala.collection.mutable

object Response {
  /* Read a response as a json string */
  def read(
      response: Identity[Response[Either[String, String]]]
  ): ujson.Value = {
    return response.body match {
      case Right(unwrapped) => {
        val v = ujson.validate(unwrapped)
        val weather = ujson.read(unwrapped)
        return weather
      }
      case Left(error) => throw new Exception(error)
    }
  }

  /* Filter relevant properties */
  def getProperties(
      jsonValues: ujson.Value
  ): List[Map[String, ujson.Value]] = {
    val features = jsonValues("features")
    var properties = mutable.Map[String, ujson.Value]()
    val dataPoints = features.arr
    .map(x => x("properties"))
    .map(x => Map("calculatedAt" -> x("calculatedAt"), "value" -> x("value")))
    .toList
    // TODO: Extend function to take an argument to filter any property of interest

    return dataPoints
  }
}
