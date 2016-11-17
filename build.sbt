name := "basecone"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava,PlayEbean)

scalaVersion := "2.11.7"
libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs
)
libraryDependencies += "nz.ac.waikato.cms.weka" % "weka-dev" % "3.9.0"
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.18"
libraryDependencies += "org.avaje.ebean" % "ebean" % "8.6.1"
libraryDependencies += "org.apache.poi" % "poi-ooxml" % "3.15"

libraryDependencies ++= Seq(
  "commons-io" % "commons-io" % "2.4"
)
