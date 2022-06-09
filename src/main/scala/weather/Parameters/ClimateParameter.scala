package weather

import scala.language.implicitConversions

object ClimateParameter extends DmiParameter {

  val ParameterId = DmiParameterValue("parameterId", "PARAMETER_ID")
  val TimeResolution = DmiParameterValue("timeResolution", "TIME_RESOLUTION")
  val StationId = DmiParameterValue("stationId", "STATION_ID")
  val Limit = DmiParameterValue("limit", "LIMIT")
}
