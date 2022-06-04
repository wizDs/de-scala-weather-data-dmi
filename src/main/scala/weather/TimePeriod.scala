package weather

sealed trait TimePeriod

final case class DateInterval(startDate: String, endDate: String)
    extends TimePeriod
final case class Date(date: String) extends TimePeriod

object TimePeriod {
  def apply(
      startDate: Option[String],
      endDate: Option[String]
  ): Option[TimePeriod] = {
    return (emptyStringToNone(startDate), emptyStringToNone(endDate)) match {
      case (Some(startDate), Some(endDate)) =>
        Some(DateInterval(startDate, endDate))
      case (Some(startDate), None) =>
        throw new IllegalArgumentException(
          "You specified a start date, but forgot to specify an end date"
        )
      case (None, Some(endDate)) =>
        throw new IllegalArgumentException(
          "You specified an end date, but forgot to specify a start date"
        )
      case _ => None
    }
  }


  def apply(date: Option[String]): Option[TimePeriod] = {
    return emptyStringToNone(date) match {
      case Some(date) => Some(Date(date))
      case _          => None
    }
  }

  private def emptyStringToNone(x: Option[String]): Option[String] =
    /*
     * This function returns None is the input string is empty
     * This is relevant because STTP's URI interpolator will ignore
     * a parameter if it has type None. If we did not do this, we could
     * end up with a query with the parameter:
     *    ...&datetime=/&...
     * which will result in an error.
     */
    x.map(_.trim()).filterNot(_.isEmpty())
}
