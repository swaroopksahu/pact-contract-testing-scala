import Dependencies.{jacksonDatabind, junit, junitTest, pactConsumerJunit, scalaTest}

name := "pact-contract-consumer"

version := "0.1"

scalaVersion := "2.12.11"

idePackagePrefix := Some("io.github.swaroopksahu")

libraryDependencies ++= Seq(
  scalaTest,
  junit,
  junitTest,
  pactConsumerJunit,
  jacksonDatabind
)
