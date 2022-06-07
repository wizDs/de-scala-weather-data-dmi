package weather

import scala.util.Try
import scala.io.Source

import java.util.zip.{GZIPOutputStream, GZIPInputStream}
import java.io.BufferedOutputStream
import java.io.BufferedInputStream
import java.io.FileOutputStream
import java.io.FileInputStream

/*
 *
 * Source: https://gist.github.com/owainlewis/1e7d1e68a6818ee4d50e
 */
object Gzip {
  /*
   *
   */
  def compress(string: String, filename: String) = {
    val target = new BufferedOutputStream(new FileOutputStream(filename))
    val gzip = new GZIPOutputStream(target)
    gzip.write(string.getBytes())
    gzip.close()
    target.close()
  }
  /*
   *
   */
  def decompress(name: String): Either[GzipError, Option[String]] = {
    readGZipFile(name) match {
      case Right(x) => x
      case Left(error) =>
        error match {
          case FileNotFound => println("File not found in path")
          case OtherErrors  =>
        }
    }
  }

  private def readGZipFile(path: String): Either[GzipError, String] = {
    val inputStream = new GZIPInputStream(
      new BufferedInputStream(new FileInputStream(path))
    )
    return Right(Source.fromInputStream(inputStream).mkString)
  }
}
