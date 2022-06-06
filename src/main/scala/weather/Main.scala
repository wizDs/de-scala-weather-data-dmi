package weather

import scala.annotation.meta.param
import scala.util.Properties.envOrNone
import sttp.client.okhttp.OkHttpSyncBackend

object Main {
  /**
   * Provides a service for aquiring meteorological data 
   * from DMI in order to get an overview of the weather 
   * for some historical period.
   * 
   * The service saves only the relevant data:
   * "calculatedAt" and "value" from the PARAMETER_ID
   * 
   */
  def main(args: Array[String]) = {
    
    val apiKey = sys.env.get("API_KEY")
    
    implicit val backend = OkHttpSyncBackend()
    val dmiClient = new DmiClient(apiKey, backend)

    var params = Parameters.readEnvVariables()
    val climateData = dmiClient.getClimateData(params.toMap)
    val properties = Response.getProperties(climateData)

    println(properties)
    val serializedString = ujson.write(climateData)
    Gzip.compress(serializedString, filename="data.gz")
  }
}
