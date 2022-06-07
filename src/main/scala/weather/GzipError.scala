package weather

sealed trait GzipError
object FileNotFound extends GzipError
case class OtherErrors(ex: Exception) extends GzipError
