name := "testinganorm"

version := "1.0"

scalaVersion := "2.11.4"

scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked", "-feature")

resolvers ++= Seq(
  "anormcypher" at "http://repo.anormcypher.org/",
  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  "org.anormcypher" %% "anormcypher" % "0.6.0"
)

mainClass in (Compile, run) := Some("main.Person")