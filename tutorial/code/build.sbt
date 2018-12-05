name := "fp-fnconf"

// global settings for this build
version in ThisBuild := "0.0.1"
organization in ThisBuild := "fnconf"
scalaVersion in ThisBuild := Versions.scalaVersion


lazy val trading = (project in file("./trading"))
  .settings(Common.settings: _*)
  .settings(libraryDependencies ++= Dependencies.tradingDependencies)

  .settings (
    fork in run := true
  )

lazy val root = (project in file(".")).
    aggregate(trading)

