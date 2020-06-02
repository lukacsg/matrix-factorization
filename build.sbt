name := "matrix-factorization"
organization := "hu.sztaki.ilab"

version := "0.2"

scalaVersion := "2.11.7"

lazy val commonDependencies = Seq(
  "org.scalatest" % "scalatest_2.11" % "3.0.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test",
  "com.typesafe" % "config" % "1.3.1",
  "org.slf4j" % "slf4j-api" % "1.7.29",
  "org.slf4j" % "slf4j-simple" % "1.7.29",
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= commonDependencies
  )

lazy val commonSettings = Seq(
  organization := "hu.sztaki.ilab",
  version := "0.2",
  scalaVersion := "2.11.7"
)
