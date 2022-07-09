import sbt.Keys.testFrameworks

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
      dependencies.jlineRead,
      dependencies.jlineTerm,
      dependencies.zio,
      dependencies.scalaTest,
      dependencies.scalaTestFreespec,
      dependencies.zioTest,
      dependencies.zioTestSbt,
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework"),
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
    val zioV = "2.0.0"
    val scalaTestV = "3.2.12"
    val jlineV = "3.21.0"

    val jlineRead = "org.jline" % "jline-reader" % jlineV
    val jlineTerm = "org.jline" % "jline-terminal-jansi" % jlineV
    val zio = "dev.zio" %% "zio" % zioV

    val scalaTest = "org.scalatest" %% "scalatest" % scalaTestV % "test"
    val scalaTestFreespec =
      "org.scalatest" %% "scalatest-freespec" % scalaTestV % "test"
    val zioTest = "dev.zio" %% "zio-test" % zioV % Test
    val zioTestSbt = "dev.zio" %% "zio-test-sbt" % zioV % Test
  }
