ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.19"

lazy val root = (project in file("."))
  .settings(
    name := "scalaFXIntro",

      libraryDependencies ++= Seq(
      // https://mvnrepository.com/artifact/org.scalafx/scalafx
      "org.scalafx" %% "scalafx" % "8.0.192-R14", // ScalaFX dependency
      // https://mvnrepository.com/artifact/org.scalafx/scalafxml-core-sfx8
      "org.scalafx" %% "scalafxml-core-sfx8" % "0.5"  // ScalaFXML dependency
    ),

    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full),
    scalacOptions += "-Xplugin-require:macroparadise"
)
