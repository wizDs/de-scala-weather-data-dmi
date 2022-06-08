package weather

sealed trait TimePeriod

final case class DateInterval(startDate: String, endDate: String)
    extends TimePeriod
final case class Date(date: String) extends TimePeriod

/* TimePeriod is a type used to determine the either dmi climate parameters
 * or met-obs parameters. Both of the services have the parameter datetime
 * which can be defined as an interval between two dates or as a single date.
 */
object TimePeriod {

  /** Constructs an Option[TimePeriod] with an interval determined by a start
    * date and end date.
    */
  def apply(
      startDate: Option[String],
      endDate: Option[String]
  ): Option[TimePeriod] = {
    return (startDate, endDate) match {
      case (Some(startDate), Some(endDate)) => {
        assert(startDate != "", "You specified an empty start date")
        assert(endDate != "", "You specified an empty end date")
        Some(DateInterval(startDate, endDate))
      }
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

  /** Constructs an Option[TimePeriod] with an interval determined by a single
    * date.
    */
  def apply(date: Option[String]): Option[TimePeriod] = {
    return date match {
      case Some(date) => {
        assert(date != "", "You specified an empty date")
        Some(Date(date))
      }
      case _ => None
    }
  }

}
