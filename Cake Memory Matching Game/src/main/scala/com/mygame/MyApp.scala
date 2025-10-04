package com.mygame

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage

class MyApp extends Application {
  override def start(primaryStage: Stage): Unit = {
    // Path to your FXML (adjust if needed)
    val resourcePath = "/com/mygame/view/MainMenu.fxml"
    val rootResource = getClass.getResource(resourcePath)

    if (rootResource == null) {
      throw new IllegalStateException(s"FXML resource not found: $resourcePath")
    }

    val loader = new FXMLLoader(rootResource)
    val root: AnchorPane = loader.load()

    val scene = new Scene(root)
    primaryStage.setTitle("Memory Match Game")
    primaryStage.setScene(scene)
    primaryStage.show()
  }
}

object MyApp {
  def main(args: Array[String]): Unit = {
    Application.launch(classOf[MyApp], args: _*)
  }
}
