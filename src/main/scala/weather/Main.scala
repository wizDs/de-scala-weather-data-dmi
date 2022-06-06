package weather

import scala.annotation.meta.param
import scala.util.Properties.envOrNone
import scala.util.Try

import java.util.zip.{GZIPOutputStream, GZIPInputStream}
import java.io.BufferedOutputStream
import java.io.BufferedInputStream
import java.io.FileOutputStream
import java.io.FileInputStream

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
    val serialized = ujson.write(climateData)
    val filename = "data.gz"
    val target = new BufferedOutputStream(new FileOutputStream(filename))
    val gzip = new GZIPOutputStream(target)
    gzip.write(serialized.getBytes())
    gzip.close()
    val test = uncompress(filename)
    
    target.close()
  }

  def uncompress(name: String): Option[String] = {
    return Try {
      val inputStream = new GZIPInputStream(
        new BufferedInputStream(new FileInputStream(name))
      )
      scala.io.Source.fromInputStream(inputStream).mkString
    }.toOption

  }

}
