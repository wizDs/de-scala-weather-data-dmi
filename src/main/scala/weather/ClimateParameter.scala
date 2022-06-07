package weather

import scala.language.implicitConversions

// TODO: Skriv kommentarer
/** */
object ClimateParameter extends DmiParameter {

  /** */
  protected case class ClimateParameter(dmiName: String, envName: String)
      extends super.Val

  /** */
  implicit def valueToClimateParameter(x: Value): ClimateParameter =
    x.asInstanceOf[ClimateParameter]

  val ParameterId = ClimateParameter("parameterId", "PARAMETER_ID")
  val TimeResolution = ClimateParameter("timeResolution", "TIME_RESOLUTION")
  val StationId = ClimateParameter("stationId", "STATION_ID")
  val Limit = ClimateParameter("limit", "LIMIT")
}
