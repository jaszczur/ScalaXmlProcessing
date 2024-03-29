
import scala.xml._
import Status._

class XmlConfigurationReader(val file : String) extends ConfigurationReader {
  val conf = XML.loadFile(file)
  

  def getResponsesFor(dn : DistinguishedName) : Seq[Response] = {
    val responses = (conf \ "response") filter { n => (n \ "@dn").toString.equals(dn.value) }
    (responses.head \ "status" ) map {statusElem =>
      Response(
        Status.withName(getOrDefault(statusElem \ "@type", "Undefined")),
        getOrDefault(statusElem \ "@progress", "0").toInt, 
        getOrDefault(statusElem \ "text", "")
      )
    }
  }
  
  private def getOrDefault(nodes : NodeSeq, default : String) : String = 
    if (nodes.isEmpty) {
      default
    } else {
      nodes.foldLeft("") { (result, node) => result + node.text }
    }
}