
import scala.xml._
import Status._

class XmlConfigurationReader(val file : String) extends ConfigurationReader {
  val conf = XML.loadFile(file)
  

  def getResponsesFor(elem : DistinguishedName) : Seq[Response] = 
    (conf \ "response") filter { n => (n \ "@dn").toString.equals(elem.value) } map {respNode =>
      Response(
        Status.withName((respNode \ "@status").toString),
        (respNode \ "@progress").toString.toInt, 
        (respNode \ "text").toString
      )
    }
}