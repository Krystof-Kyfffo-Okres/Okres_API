scalaVersion := "2.13.8"
name := "openai"
organization := "spse"
version := "1.0"
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"
val AkkaVersion = "2.7.0"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % AkkaVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % AkkaVersion % Test,
  "com.typesafe.akka" %% "akka-protobuf-v3" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % "10.5.0"
)