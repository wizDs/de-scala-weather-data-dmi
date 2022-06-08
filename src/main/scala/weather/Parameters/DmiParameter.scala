package weather

import scala.language.implicitConversions

trait DmiParameter extends Enumeration {

  /** Extends the Enum.Val, making it possible for DmiParameter to have two
    * entries, one for parameter name from dmi service and one for the
    * environment variable name from local machine.This facilitates creating a
    * parameter Map with the dmi parameter names and an assigned value via the
    * environment variables
    */
  protected case class DmiParameter(dmiName: String, envName: String)
      extends super.Val

  /** */
  implicit def valueToParameter(x: Value): DmiParameter =
    x.asInstanceOf[DmiParameter]
}
