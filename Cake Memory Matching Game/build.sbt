ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.14"

lazy val root = (project in file("."))
  .settings(
    name := "scala-javafx-game",
    libraryDependencies ++= Seq(
      "org.openjfx" % "javafx-base" % "21" classifier "win",
      "org.openjfx" % "javafx-controls" % "21" classifier "win",
      "org.openjfx" % "javafx-fxml" % "21" classifier "win",
      "org.openjfx" % "javafx-graphics" % "21" classifier "win"
    )
  )
