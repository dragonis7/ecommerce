
name := "ecommerce"

version := "1.0"

lazy val `ecommerce` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

resolvers := ("Atlassian Releases" at "https://maven.atlassian.com/public/") +: resolvers.value

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq(
  "com.mohiva" %% "play-silhouette" % "4.0.0-BETA4",
  "com.mohiva" %% "play-silhouette-password-bcrypt" % "4.0.0-BETA4",
  "com.mohiva" %% "play-silhouette-persistence-memory" % "4.0.0-BETA4",
  "org.webjars" %% "webjars-play" % "2.5.0",
  "net.codingwell" %% "scala-guice" % "4.0.1",
  "com.iheart" %% "ficus" % "1.2.0",
  "com.adrianhurt" %% "play-bootstrap" % "1.0-P25-B3",
  "com.mohiva" %% "play-silhouette-testkit" % "4.0.0-BETA4" % "test",
  specs2 % Test,
  cache,
  filters
)


libraryDependencies ++= Seq(
  "com.typesafe.play" %% "anorm" % "2.5.0",
  "org.eu.acolyte" %% "jdbc-scala" % "1.0.35-j7p" % "test")

libraryDependencies ++= Seq( jdbc , cache , ws   , specs2 % Test )

libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.8.10.1"



libraryDependencies += evolutions



libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "2.0.0"
)

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "3.8" % "test")



scalacOptions in Test ++= Seq("-Yrangepos")

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )



routesGenerator := InjectedRoutesGenerator

scalacOptions ++= Seq(
  "-deprecation", // Emit warning and location for usages of deprecated APIs.
  "-feature", // Emit warning and location for usages of features that should be imported explicitly.
  "-unchecked", // Enable additional warnings where generated code depends on assumptions.
  "-Xfatal-warnings", // Fail the compilation if there are any warnings.
  "-Xlint", // Enable recommended additional warnings.
  "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver.
  "-Ywarn-dead-code", // Warn when dead code is identified.
  "-Ywarn-inaccessible", // Warn about inaccessible types in method signatures.
  "-Ywarn-nullary-override", // Warn when non-nullary overrides nullary, e.g. def foo() over def foo.
  "-Ywarn-numeric-widen" // Warn when numerics are widened.
)

//********************************************************
// Scalariform settings
//********************************************************

