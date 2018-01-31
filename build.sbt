name := """Akka-Lab-Exercises"""

scalaVersion := "2.11.8"
// scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.8",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.8",
  "com.typesafe.akka" %% "akka-stream" % "2.5.8",
  "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.8",
  "org.scalatest" %% "scalatest" % "3.0.4" % "test",
  "junit" % "junit" % "4.12",
  "com.novocode" % "junit-interface" % "0.11",
  "org.hamcrest" % "hamcrest-all" % "1.3",
  "com.mashape.unirest" % "unirest-java" % "1.4.9",
  "com.typesafe.akka" % "akka-remote_2.11" % "2.5.8"
)

