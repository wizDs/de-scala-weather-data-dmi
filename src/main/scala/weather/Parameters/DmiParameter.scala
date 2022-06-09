package weather

import scala.language.implicitConversions

/** Implementing the dmi parameters as an Enum ensures type safety and makes it
  * easy to access the dmi parameters suported in DmiClient. The DmiParameter is
  * designed as a trait because the DmiClient supports multiple parameter types,
  * namely ClimateParameter and MetObsParameter, which is used in two seperate
  * request-types in DmiClient. At the end of the day all the request strings
  * from sttp is implemented as an URI type, which supports interpolation of a
  * Map of dmi parametername and an assigned value defined by an environment
  * variable.
  */
trait DmiParameter extends Enumeration {

  /** This case class is introduced to make it possible for each enum value to
    * have two entries, one for parameter name from dmi service and one for the
    * environment variable name from local machine. This facilitates creating a
    * parameter Map with the dmi parameter names and an assigned value via the
    * environment variables.
    */
  /** @param dmiName
    *   is the parameter name from confluence documentation
    * @param envName
    *   is the environment name, set on the local machine
    */
  protected case class DmiParameterValue(dmiName: String, envName: String)
      extends super.Val

  implicit def valueToParameter(x: Value): DmiParameterValue =
    x.asInstanceOf[DmiParameterValue]
}
