package weather

import scala.util.Try

import java.util.zip.{GZIPOutputStream, GZIPInputStream}
import java.io.BufferedOutputStream
import java.io.BufferedInputStream
import java.io.FileOutputStream
import java.io.FileInputStream

object Gzip {
  def compress(string: String, filename: String) = {
    val target = new BufferedOutputStream(new FileOutputStream(filename))
    val gzip = new GZIPOutputStream(target)
    gzip.write(string.getBytes())
    gzip.close()
    target.close()
  }

  def decompress(name: String): Option[String] = {
    return Try {
      val inputStream = new GZIPInputStream(
        new BufferedInputStream(new FileInputStream(name))
      )
      scala.io.Source.fromInputStream(inputStream).mkString
    }.toOption

  }
}