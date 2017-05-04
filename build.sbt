name := """Akka-Lab-Exercises"""

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.0",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.0",
  "com.typesafe.akka" %% "akka-stream" % "2.5.0",
  "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.0",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "junit" % "junit" % "4.12",
  "com.novocode" % "junit-interface" % "0.11",
  "org.hamcrest" % "hamcrest-all" % "1.3",
  "com.mashape.unirest" % "unirest-java" % "1.4.9",
  "com.typesafe.akka" % "akka-remote_2.11" % "2.5.0"
)

