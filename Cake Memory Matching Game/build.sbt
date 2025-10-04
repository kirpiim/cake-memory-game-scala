ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.14"

lazy val root = (project in file("."))
  .settings(
    name := "scala-javafx-game",

    // JavaFX libraries (auto-select OS)
    libraryDependencies ++= {
      val os = System.getProperty("os.name").toLowerCase match {
        case s if s.contains("win") => "win"
        case s if s.contains("mac") => "mac"
        case _ => "linux"
      }
      Seq(
        "org.openjfx" % "javafx-base" % "21" classifier os,
        "org.openjfx" % "javafx-controls" % "21" classifier os,
        "org.openjfx" % "javafx-fxml" % "21" classifier os,
        "org.openjfx" % "javafx-graphics" % "21" classifier os
      )
    },

    // Make sure resources are recognized and copied
    Compile / resourceDirectory := baseDirectory.value / "src" / "main" / "resources",
    Compile / unmanagedResourceDirectories += baseDirectory.value / "src" / "main" / "resources",

    // 🧩 Force copy resources before every compile
    Compile / compile := {
      val resDir = (Compile / resourceDirectory).value
      val classDir = (Compile / classDirectory).value
      import java.nio.file.{Files, StandardCopyOption}
      import scala.jdk.CollectionConverters._

      println(s"Forcing resource copy from: $resDir to: $classDir")

      if (resDir.exists()) {
        Files.walk(resDir.toPath).iterator().asScala.foreach { path =>
          val target = classDir.toPath.resolve(resDir.toPath.relativize(path))
          if (Files.isDirectory(path)) Files.createDirectories(target)
          else Files.copy(path, target, StandardCopyOption.REPLACE_EXISTING)
        }
      }
      (Compile / compile).value
    },

    //  Enable JavaFX modules correctly when running
    fork := true,
    Compile / run / forkOptions := {
      val base = (Compile / run / forkOptions).value

      // Path where sbt stores JavaFX jars
      val cp = (Compile / dependencyClasspath).value.map(_.data.getAbsolutePath)
      val jfxPath = cp.filter(_.contains("javafx")).mkString(java.io.File.pathSeparator)

      // Force-enable all needed JavaFX modules
      val jfxModules = "javafx.controls,javafx.fxml,javafx.graphics,javafx.base"

      base.withRunJVMOptions(Vector(
        "--module-path", jfxPath,
        "--add-modules", jfxModules
      ))
    }

  )
