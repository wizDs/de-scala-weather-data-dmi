package weather

/* An Enum defining all weather params except datetime */
object ClimateParameterNames extends Enumeration {
  protected case class ClimateParameterName(dmiName: String, envName: String)
      extends super.Val
  import scala.language.implicitConversions
  implicit def valueToClimateParameterName(x: Value): ClimateParameterName =
    x.asInstanceOf[ClimateParameterName]
  val ParameterId = ClimateParameterName("parameterId", "PARAMETER_ID")
  val TimeResolution = ClimateParameterName("timeResolution", "TIME_RESOLUTION")
  val StationId = ClimateParameterName("stationId", "STATION_ID")
  val Limit = ClimateParameterName("limit", "LIMIT")
}