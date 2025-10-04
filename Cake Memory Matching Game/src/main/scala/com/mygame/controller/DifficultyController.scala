package com.mygame.controller

import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.layout.AnchorPane
import javafx.event.ActionEvent

class DifficultyController {

  @FXML private var mainLayout: AnchorPane = _

  @FXML
  def handleEasyButton(event: ActionEvent): Unit = {
    loadGame("/com/mygame/view/EasyGame.fxml")
  }

  @FXML
  def handleHardButton(event: ActionEvent): Unit = {
    loadGame("/com/mygame/view/HardGame.fxml")
  }

  private def loadGame(fxmlPath: String): Unit = {
    val resource = getClass.getResource(fxmlPath)
    if (resource == null) {
      throw new IllegalStateException(s"FXML resource not found: $fxmlPath")
    }
    val loader = new FXMLLoader(resource)
    val roots = loader.load[AnchorPane]()
    mainLayout.getChildren.setAll(roots)
  }
}
