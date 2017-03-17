name := """Akka-Lab-Exercises-1"""

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.11",
  "junit" % "junit" % "4.12",
  "com.novocode" % "junit-interface" % "0.11",
  "com.typesafe.akka" % "akka-testkit_2.11" % "2.4.16",
  "org.hamcrest" % "hamcrest-all" % "1.3"
  
)

licenses := Seq(("CC0", url("http://creativecommons.org/publicdomain/zero/1.0")))
