name := "hello-world-scala"
version := "1.0-SNAPSHOT"
scalaVersion := "2.13.15"
libraryDependencies ++= Seq(
  "com.couchbase.client" %% "scala-client" % "1.7.4",
  "org.slf4j" % "slf4j-api" % "2.0.9",
  "org.slf4j" % "slf4j-simple" % "2.0.9"
)
