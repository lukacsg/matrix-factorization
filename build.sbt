name := "matrix-factorization"
organization := "hu.sztaki.ilab"

version := "0.1"

scalaVersion := "2.11.7"

lazy val commonDependencies = Seq(
  "org.scalatest" % "scalatest_2.11" % "3.0.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test",
  "com.typesafe" % "config" % "1.3.1"
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= commonDependencies
  )

lazy val commonSettings = Seq(
  organization := "hu.sztaki.ilab",
  version := "0.1",
  scalaVersion := "2.11.7"
)
