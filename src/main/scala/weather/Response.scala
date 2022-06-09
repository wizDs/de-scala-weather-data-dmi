package weather

import sttp.client._
import scala.collection.mutable

/** The DmiClient is using this utility module to validate, read and filter the
  * response from a http request. This is not part of the DMI Client as a system
  * design choice as it can be used in other contexts, e.g. extending to other
  * modules.
  */
object Response {

  /** This validates that the response from a http request has a valid json
    * format. If the response is not valid it returns an exception else it reads
    * the response and returns a Json structure.
    *
    * @param response
    *   the response from a http request
    * @return
    *   a json structure
    */
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

  /** This filters the raw json structure to only contain a time stamp and an
    * indicator value, e.g. mean temperature. the type of indicator value is
    * controlled using the PARAMETER_ID environment variable
    * @param jsonValues
    *   is a json structure, read from a response from a http request
    * @return
    *   a list of Maps with the properties 'calculatedAt' and 'value'
    */

  def getProperties(
      jsonValues: ujson.Value
  ): List[Map[String, ujson.Value]] = {
    val features = jsonValues("features")
    var properties = mutable.Map[String, ujson.Value]()
    val dataPoints = features.arr
      .map(x => x("properties"))
      .map(x => Map("calculatedAt" -> x("calculatedAt"), "value" -> x("value")))
      .toList

    return dataPoints
  }
}
