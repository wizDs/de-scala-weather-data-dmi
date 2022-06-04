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
  def main(args: Array[String]) = {
    
    val apiKey = sys.env.get("API_KEY")
    
    implicit val backend = OkHttpSyncBackend()
    val dmiClient = new DmiClient(apiKey, backend)

    var params = Parameters.readEnvVariables()
    println(params)
    val climateData = dmiClient.getClimateData(params.toMap)
    val properties =
      Response.getProperties(climateData, List("calculatedAt", "value"))

    val serialized = ujson.write(climateData)
    val filename = "test2.gz"
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
