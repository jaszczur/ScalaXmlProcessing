object Main {
  def main(attrs : Array[String]) : Unit = {
    val confReader : ConfigurationReader = new XmlConfigurationReader("src/main/resources/config.xml")
    confReader.getResponsesFor(DistinguishedName("PLMN/RNC-1")) foreach {resp =>
      println(resp)
    }
  }
}