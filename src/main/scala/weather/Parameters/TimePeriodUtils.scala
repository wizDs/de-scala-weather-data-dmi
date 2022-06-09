package weather

/* Transforms a TimePeriod (DateInterval or Date) to DMI
 * datetime param which is nessesary since the DmiClient
 * only inputs dmi-parameters as Map.
 */
object TimePeriodUtils {
  def toDmiTime(t: Option[TimePeriod]): Option[String] = {
    return t match {
      case Some(DateInterval(startDate, endDate)) =>
        Some(s"$startDate/$endDate")
      case Some(Date(date)) => Some(s"$date")
      case _                => None
    }
  }
}
