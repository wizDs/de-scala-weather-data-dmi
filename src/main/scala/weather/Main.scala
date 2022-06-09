package weather

import scala.annotation.meta.param
import scala.util.Properties.envOrNone
import sttp.client.okhttp.OkHttpSyncBackend

/** This calls the DmiClient that acquires whether data which is persisted as a
  * Gzip file, controled by the environment variable PATH_DATA. However, if the
  * PATH_DATA is not set, the default path is ./data.gz. Even though the Client
  * supports both MetObs and ClimateData, this only calls the ClimateData.
  */
object Main {

  def main(args: Array[String]) = {

    val apiKey = sys.env.get("API_KEY")
    val pathData = sys.env.get("PATH_DATA").getOrElse("data.gz")
    println(pathData)

    implicit val backend = OkHttpSyncBackend()
    val dmiClient = new DmiClient(apiKey)

    var climateParams = Parameters.envVariablesToMap(ClimateParameter)
    val climateData = dmiClient.getClimateData(climateParams)
    val climateProperties = Response.getProperties(climateData)

    val climateJsonString = ujson.write(climateProperties)
    Gzip.compress(climateJsonString, path = pathData)
    println(climateProperties)

  }
}
