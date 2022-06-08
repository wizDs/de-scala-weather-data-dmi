package weather

import scala.language.implicitConversions

/** MetObsParameter is an Enum with all documented parameters for the met-obs
  * service. Implementing the parameters as an Enum ensures type safety and
  * makes it easy to loop through.
  */
object MetObsParameter extends DmiParameter {

  val ParameterId = DmiParameter("parameterId", "PARAMETER_ID")
  val TimeResolution = DmiParameter("timeResolution", "TIME_RESOLUTION")
  val StationId = DmiParameter("stationId", "STATION_ID")
  val Limit = DmiParameter("limit", "LIMIT")

}
