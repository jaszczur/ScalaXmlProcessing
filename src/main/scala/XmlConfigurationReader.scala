
import scala.xml._
import Status._

class XmlConfigurationReader(val file : String) extends ConfigurationReader {
  val conf = XML.loadFile(file)
  

  def getResponsesFor(elem : DistinguishedName) : Seq[Response] = {
    val responses = (conf \ "response") filter { n => (n \ "@dn").toString.equals(elem.value) }
    (responses.head \ "status" ) map {statusElem =>
      println((statusElem \ "@type").toString)
      Response(
        Status.withName((statusElem \ "@type").toString),
        (statusElem \ "@progress").toString.toInt, 
        (statusElem \ "text").toString
      )
    }
  }
}