// ZIO apps call java.lang.System.exit(code) https://github.com/zio/zio/issues/6895
// run / fork := true
// forking detaches terminal; can't manually test with sbt run

addCommandAlias("fmt", ";scalafmtSbt;scalafmtAll")

lazy val root = project
  .in(file("."))
  .settings(
    name := "term-grid",
    version := "0.1",
    scalaVersion := "3.1.3",
    organization := "info.ditrapani",
    maintainer := "lj-ditrapani",
    scalacOptions := compilerOptions,
    libraryDependencies ++= Seq(
      dependencies.jlineTerm,
      dependencies.jlineRead,
      dependencies.scalaTest,
      dependencies.scalaTestFreespec,
    ),
  )
  .enablePlugins(JavaAppPackaging)

lazy val compilerOptions =
  Seq(
    "-deprecation",
    "-encoding",
    "UTF-8",
    "-unchecked",
    "-Yexplicit-nulls",
    "-Ysafe-init",
    "-new-syntax",
    "-indent",
    // "-rewrite",
  )

lazy val dependencies =
  new {
    val scalaTestV = "3.2.12"
    val jlineV = "3.21.0"

    val jlineTerm = "org.jline" % "jline-terminal-jansi" % jlineV
    val jlineRead = "org.jline" % "jline-reader" % jlineV
    val scalaTest = "org.scalatest" %% "scalatest" % scalaTestV % "test"
    val scalaTestFreespec =
      "org.scalatest" %% "scalatest-freespec" % scalaTestV % "test"
  }
