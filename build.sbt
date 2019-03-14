name := "invoker-game"

version := "0.1"

scalaVersion := "2.12.8"

Compile/mainClass := Some("dev.zerosum.invokergame.Main")

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % "2.5.21",
  "org.scalactic" %% "scalactic" % "3.0.5",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)