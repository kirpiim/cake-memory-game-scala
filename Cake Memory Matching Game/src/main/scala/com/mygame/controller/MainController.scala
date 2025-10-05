package com.mygame.controller

import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.Button
import javafx.scene.layout.AnchorPane
import javafx.event.ActionEvent
import javafx.scene.Scene
import javafx.stage.Stage

class MainController {

  @FXML private var mainLayout: AnchorPane = _
  @FXML private var playgameButton: Button = _
  @FXML private var rulesButton: Button = _

  @FXML
  def handlePlayGameButton(event: ActionEvent): Unit = {
    loadScene("/com/mygame/view/GameDifficulty.fxml")
  }

  @FXML
  def handleRulesButton(event: ActionEvent): Unit = {
    loadScene("/com/mygame/view/Rules.fxml")
  }

  @FXML
  def handleMainPageButton(event: ActionEvent): Unit = {
    loadScene("/com/mygame/view/MainMenu.fxml")
  }

  /** Loads a new FXML and sets it as the current window’s scene. */
  private def loadScene(path: String): Unit = {
    val resource = getClass.getResource(path)
    if (resource == null) {
      throw new IllegalStateException(s"FXML resource not found: $path")
    }

    val loader = new FXMLLoader(resource)
    val root = loader.load[javafx.scene.Parent]()
    val stage = mainLayout.getScene.getWindow.asInstanceOf[Stage]
    val scene = new Scene(root)

    stage.setScene(scene)
    stage.show()
  }
}
