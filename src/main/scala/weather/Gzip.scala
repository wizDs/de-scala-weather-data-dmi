package weather

import scala.util.{Try, Success, Failure}
import scala.io.Source

import java.util.zip.{GZIPOutputStream, GZIPInputStream}
import java.io.BufferedOutputStream
import java.io.BufferedInputStream
import java.io.FileOutputStream
import java.io.FileInputStream

/** This module compresses json strings to Source:
  * https://gist.github.com/owainlewis/1e7d1e68a6818ee4d50e
  */
object Gzip {
  /*
   *
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
  def decompress(path: String): Either[Throwable, String] = {
    return readGZipFile(path) match {
      case Success(content) => Right(content)
      case Failure(error) => {
        println(s"Failed to decompress file: $path")
        println(error)
        Left(error)
      }
    }
  }

  private def readGZipFile(path: String): Try[String] = {
    Try {
      val inputStream = new GZIPInputStream(
        new BufferedInputStream(new FileInputStream(path))
      )
      Source.fromInputStream(inputStream).mkString
    } // .toOption
  }

  private def writeGZipFile(content: String, path: String): Unit = {
    Try {
      val target = new BufferedOutputStream(new FileOutputStream(path))
      val gzip = new GZIPOutputStream(target)
      gzip.write(content.getBytes())
      gzip.close()
      target.close()
    }
  }
}
