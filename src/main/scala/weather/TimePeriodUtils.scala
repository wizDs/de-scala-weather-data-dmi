package weather

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