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
      dependencies.jline,
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

    val jline = "org.jline" % "jline-terminal" % "3.21.0"
    val scalaTest = "org.scalatest" %% "scalatest" % scalaTestV % "test"
    val scalaTestFreespec =
      "org.scalatest" %% "scalatest-freespec" % scalaTestV % "test"
  }
