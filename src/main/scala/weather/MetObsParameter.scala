package weather

import scala.language.implicitConversions

object MetObsParameter extends DmiParameter {
  protected case class MetObsParameter(dmiName: String, envName: String)
      extends super.Val

  implicit def valueToMetObsParameter(x: Value): MetObsParameter =
    x.asInstanceOf[MetObsParameter]

  val ParameterId = MetObsParameter("parameterId", "PARAMETER_ID")
  val TimeResolution = MetObsParameter("timeResolution", "TIME_RESOLUTION")
  val StationId = MetObsParameter("stationId", "STATION_ID")
  val Limit = MetObsParameter("limit", "LIMIT")
  
}