package weather
import scala.collection.mutable

object Parameters {
  def readEnvVariables(): scala.collection.mutable.Map[String, Option[String]] = {
    val params = scala.collection.mutable.Map[String, Option[String]]()

    for (p <- ClimateParameterNames.values)
      params += (p.dmiName -> sys.env.get(p.envName))

    val timePeriod =
      TimePeriod(sys.env.get("START_DATE"), sys.env.get("END_DATE"))

    val timePeriodOrDate = 
      if (timePeriod.isEmpty) TimePeriod(sys.env.get("DATE")) else timePeriod
    
    params += ("datetime" -> TimePeriodUtils.toDmiTime(timePeriodOrDate))
    return params
  }
}
