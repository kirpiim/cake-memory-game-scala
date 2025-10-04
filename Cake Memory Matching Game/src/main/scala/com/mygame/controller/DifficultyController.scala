package com.mygame.controller

import scalafx.Includes._
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import scalafxml.core.macros.sfxml
import javafx.scene.layout.AnchorPane
import javafx.event.ActionEvent
import javafx.fxml.FXML

@sfxml
class DifficultyController(
                            private val mainLayout: AnchorPane
                          ) {

  @FXML
  def handleEasyButton(event: ActionEvent): Unit = {
    loadGame("/com.mygame.view/EasyGame.fxml")
  }

  @FXML
  def handleHardButton(event: ActionEvent): Unit = {
    loadGame("/com.mygame.view/HardGame.fxml")
  }

  private def loadGame(fxmlPath: String): Unit = {
    val resource = getClass.getResource(fxmlPath)
    if (resource == null) {
      throw new IllegalStateException(s"FXML resource not found: $fxmlPath")
    }
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()
    val roots = loader.getRoot[javafx.scene.layout.AnchorPane]
    mainLayout.getChildren.setAll(roots)
  }
}

