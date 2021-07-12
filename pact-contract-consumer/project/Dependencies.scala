import sbt._

object Dependencies {
  val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % Test
  val junit = "junit" % "junit" % "4.12" % Test
  val junitTest = "com.novocode" % "junit-interface" % "0.10" % Test exclude("junit", "junit-dep")
  val pactConsumerJunit = "au.com.dius.pact.consumer" % "junit" % "4.1.18"
  val jacksonDatabind = "com.fasterxml.jackson.core" % "jackson-databind" % "2.12.1"
}