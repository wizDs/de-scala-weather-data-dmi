package weather
import scala.collection.mutable

/** Module for acquiring Dmi parameters via environment variables.
  */
object Parameters {

  /** Reads all DmiParameters conditioned whether its ClimateParameter or
    * MetObsParameter. This is due to having implemented two services in the Dmi
    * Client
    */
  def readEnvVariables(parameter: DmiParameter): Map[String, Option[String]] = {
    var params = mutable.Map[String, Option[String]]()

    return parameter match {
      case ClimateParameter => {
        for (p <- ClimateParameter.values)
          params += (p.dmiName -> sys.env.get(p.envName))

        params += ("datetime" -> dmiDatetime)
        params.toMap
      }
      case MetObsParameter => {
        for (p <- MetObsParameter.values)
          params += (p.dmiName -> sys.env.get(p.envName))

        params += ("datetime" -> dmiDatetime)
        params.toMap
      }
    }
  }

  private val dmiDatetime: Option[String] = {
    val timePeriod =
      TimePeriod(sys.env.get("START_DATE"), sys.env.get("END_DATE"))

    val timePeriodOrDate =
      if (timePeriod.isEmpty) TimePeriod(sys.env.get("DATE")) else timePeriod

    TimePeriodUtils.toDmiTime(timePeriodOrDate)
  }
}
