package weather

import scala.util.{Try, Success, Failure}
import scala.io.Source

import java.util.zip.{GZIPOutputStream, GZIPInputStream}
import java.io.BufferedOutputStream
import java.io.BufferedInputStream
import java.io.FileOutputStream
import java.io.FileInputStream

/** This module provides utilities to compress json strings to gzip and vice
  * versa. Source: https://gist.github.com/owainlewis/1e7d1e68a6818ee4d50e
  */
object Gzip {

  /** @param content
    * @param path
    */
  def compress(content: String, path: String) = {

    val target = new BufferedOutputStream(new FileOutputStream(path))
    val gzip = new GZIPOutputStream(target)
    gzip.write(content.getBytes())
    gzip.close()
    target.close()
  }

  /*
   *
   */
  def decompress(path: String): Option[String] = {
    return Try {
      val inputStream = new GZIPInputStream(
        new BufferedInputStream(new FileInputStream(path))
      )
      scala.io.Source.fromInputStream(inputStream).mkString
    }.toOption

  }
}
