package weather

import scala.annotation.meta.param
import scala.util.Properties.envOrNone
import sttp.client.okhttp.OkHttpSyncBackend

object Main {

  /** Provides a service for aquiring meteorological data from DMI in order to
    * get an overview of the weather for some historical period.
    *
    * The service saves only the relevant data: "calculatedAt" and "value" from
    * the PARAMETER_ID
    */
  def main(args: Array[String]) = {

    val apiKey = sys.env.get("API_KEY")

    implicit val backend = OkHttpSyncBackend()
    val dmiClient = new DmiClient(apiKey)

    var climateParams = Parameters.readEnvVariables(ClimateParameter)
    val climateData = dmiClient.getClimateData(climateParams)
    val climateProperties = Response.getProperties(climateData)

    val climateJsonString = ujson.write(climateProperties)
    Gzip.compress(climateJsonString, filename = "climate_data.gz")
    println(climateProperties)

    var metObsParams = Parameters.readEnvVariables(MetObsParameter)
    val metObsData = dmiClient.getClimateData(metObsParams)
    val metObsProperties = Response.getProperties(climateData)

    val metObsJsonString = ujson.write(metObsProperties)
    Gzip.compress(metObsJsonString, filename = "metobs_data.gz")
    println(metObsProperties)
  }
}
