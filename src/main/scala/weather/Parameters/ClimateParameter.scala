package weather

import scala.language.implicitConversions

/** ClimateParameter is an Enum with all documented parameters for the climate
  * service. Implementing the parameters as an Enum ensures type safety and
  * makes it easy to loop through.
  */
object ClimateParameter extends DmiParameter {

  val ParameterId = DmiParameter("parameterId", "PARAMETER_ID")
  val TimeResolution = DmiParameter("timeResolution", "TIME_RESOLUTION")
  val StationId = DmiParameter("stationId", "STATION_ID")
  val Limit = DmiParameter("limit", "LIMIT")
}
