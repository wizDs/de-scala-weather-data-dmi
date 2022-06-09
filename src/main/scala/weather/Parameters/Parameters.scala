package weather
import scala.collection.mutable

/** Since all request strings from sttp is implemented as an URI type, which
  * supports interpolation of a Map of dmi parametername and an assigned value
  * defined by an environment variable. This module acquires this Map of dmi
  * parameters and environment variable value, which is interpolated into the
  * request/URI string.
  */
object Parameters {

  /** Gets a Map of dmi parameters and environment variable value, which can be
    * interpolated into request string in the DmiClient.
    * @param parameter:
    *   is a DmiParameter, with two subtypes ClimateParameter and
    *   MetObsParameter
    */
  def envVariablesToMap(
      parameter: DmiParameter
  ): Map[String, Option[String]] = {
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
