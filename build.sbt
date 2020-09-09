name := "scalaStudy #1"
version := "1.0"
scalaVersion := "2.12.7"
val AkkaVersion = "2.5.31"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % AkkaVersion

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.0"

libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % AkkaVersion

libraryDependencies += "com.typesafe.akka" %% "akka-remote" % AkkaVersion

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2"

libraryDependencies += "com.syncthemall" % "boilerpipe" % "1.2.2"

libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.5.12"

libraryDependencies += "com.jason-goodwin" % "better-monads" % "0.4.1"

libraryDependencies += "com.typesafe.akka" %% "akka-cluster" % AkkaVersion

libraryDependencies += "com.typesafe.akka" %% "akka-contrib" % AkkaVersion

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime"
libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" %  AkkaVersion

//libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion
//addSbtPlugin("io.get-coursier" % "sbt-coursier" % "1.0.3")

