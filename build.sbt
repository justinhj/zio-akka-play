import sbt._

lazy val zioakka = (project in file(".")).
  settings (
    name := "zioakka",
    organization := "justinhj",
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.12.8"
    // add other settings here
  )

/* scala versions and options */
scalaVersion := "2.12.8"

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.6")

// These options will be used for *all* versions.
scalacOptions ++= Seq(
  "-deprecation"
  , "-unchecked"
  , "-encoding", "UTF-8"
  , "-Xlint"
  , "-Xverify"
  , "-feature"
  ,"-Ypartial-unification"
  //,"-Xfatal-warnings"
  , "-language:_"
  //,"-optimise"
)

javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation", "-source", "1.7", "-target", "1.7")

val CatsVersion = "2.0.0-M1"
val CatsEffectVersion = "1.3.0"
val MonixVersion = "3.0.0-M3"
val ScalaZVersion = "7.3.0-M29"
val ZIOVersion = "1.0.0-RC8-12"
val ShapelessVersion = "2.3.3"
val FS2Version = "1.0.4"
val akkaVersion = "2.5.23"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.1",
  // -- testing --
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  // -- Logging --
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0",
  // li haoyi ammonite repl embed
  "com.lihaoyi" % "ammonite" % "1.6.7" % "test" cross CrossVersion.full,
  // Actors
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  // Zio
  "dev.zio" %% "zio" % ZIOVersion
)

resolvers ++= Seq(
  "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
  "Secured Central Repository" at "https://repo1.maven.org/maven2",
  Resolver.sonatypeRepo("snapshots")
)

// ammonite repl
sourceGenerators in Test += Def.task {
  val file = (sourceManaged in Test).value / "amm.scala"
  IO.write(file, """object amm extends App { ammonite.Main().run() }""")
  Seq(file)
}.taskValue

