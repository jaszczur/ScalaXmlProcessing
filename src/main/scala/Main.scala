object Main {
  val confReader : ConfigurationReader = new XmlConfigurationReader("src/main/resources/config.xml")
  
  def main(attrs : Array[String]) : Unit = {
    printResponsesFor("PLMN/RNC-1")
    printResponsesFor("PLMN/RNC-2")
  }
  
  private def printResponsesFor(dn: String) : Unit = {
    println("Responses for " + dn)
    confReader.getResponsesFor(DistinguishedName(dn)) foreach {resp =>
      println("  " + resp)
    }
    println("end")
  }
}