
case class DistinguishedName(val value : String)

object Status extends Enumeration {
  type Status = Value
  val Ongoing = Value("Ongoing")
  val Successfull = Value("Successfull")
  val Failed = Value("Failed")
}
import Status._

case class Response(val status : Status, val progress : Int, val text : String)

trait ConfigurationReader {
  def getResponsesFor(elem : DistinguishedName) : Seq[Response]
}
