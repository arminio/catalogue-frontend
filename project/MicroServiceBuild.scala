import sbt._

object MicroServiceBuild extends Build with MicroService {
  override val appName = "catalogue-frontend"
  override lazy val plugins: Seq[Plugins] = Seq()
  override lazy val appDependencies: Seq[ModuleID] = AppDependencies()
}

private object AppDependencies {
  import play.PlayImport._
  import play.core.PlayVersion

  val compile = Seq(
    ws,
    "uk.gov.hmrc" %% "frontend-bootstrap" % "6.4.0",
    "uk.gov.hmrc" %% "play-config" % "2.0.1",
    "uk.gov.hmrc" %% "play-json-logger" % "2.1.1",
    "uk.gov.hmrc" %% "play-health" % "1.1.0",
    "uk.gov.hmrc" %% "govuk-template" % "4.0.0",
    "uk.gov.hmrc" %% "play-ui" % "4.2.0",
    "uk.gov.hmrc" %% "url-builder" % "1.0.0",
    "org.apache.httpcomponents" % "httpcore" % "4.3.2",
    "org.apache.httpcomponents" % "httpclient" % "4.3.5"
  )

  trait TestDependencies {
    lazy val scope: String = "test"
    lazy val test : Seq[ModuleID] = ???
  }

  object Test {
    def apply() = new TestDependencies {
      override lazy val test = Seq(
        "uk.gov.hmrc" %% "hmrctest" % "1.4.0" % scope,
        "org.scalatest" %% "scalatest" % "2.2.2" % scope,
        "org.scalatestplus" %% "play" % "1.2.0" % scope,
        "org.pegdown" % "pegdown" % "1.4.2" % scope,        
        "com.typesafe.play" %% "play-test" % PlayVersion.current % scope,
        "com.github.tomakehurst" % "wiremock" % "1.52" % "test"
      )
    }.test
  }

  def apply() = compile ++ Test()
}

