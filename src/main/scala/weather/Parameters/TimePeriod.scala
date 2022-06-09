package weather

sealed trait TimePeriod

final case class DateInterval(startDate: String, endDate: String)
    extends TimePeriod
final case class Date(date: String) extends TimePeriod

/** TimePeriod is used in both dmi climate parameters and met-obs parameters.
  * Both of the services have the parameter datetime which can be defined as an
  * interval between two dates or as a single date. Time Period is implemented
  * as a algebraic data type because one can use pattern matching for each of
  * the cases defined in the confluence documentation.
  */
object TimePeriod {

  /** Constructs an TimePeriod with a start date and an end date. The
    * constructor follows the cases given in the confluence documentation
    *
    * @param startDate
    *   is the start date of the date interval
    * @param endDate
    *   is the end date of the date interval
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

  /** Constructs an a TimePeriod with a single date.
    *
    * @param date
    *   is a single date as a time interval
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
