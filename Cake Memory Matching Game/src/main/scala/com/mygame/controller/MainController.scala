package com.mygame.controller

import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.Button
import javafx.scene.layout.AnchorPane
import javafx.event.ActionEvent

class MainController {

  @FXML private var mainLayout: AnchorPane = _
  @FXML private var playgameButton: Button = _
  @FXML private var rulesButton: Button = _

  @FXML
  def handlePlayGameButton(event: ActionEvent): Unit = {
    showGameDifficulty()
  }

  @FXML
  def handleRulesButton(event: ActionEvent): Unit = {
    showRules()
  }

  @FXML
  def handleMainPageButton(event: ActionEvent): Unit = {
    showMainPage()
  }

  private def showGameDifficulty(): Unit = {
    loadScene("/com/mygame/view/GameDifficulty.fxml")
  }

  private def showRules(): Unit = {
    loadScene("/com/mygame/view/Rules.fxml")
  }

  private def showMainPage(): Unit = {
    loadScene("/com/mygame/view/MainMenu.fxml")
  }

  private def loadScene(path: String): Unit = {
    val resource = getClass.getResource(path)
    if (resource == null) {
      throw new IllegalStateException(s"FXML resource not found: $path")
    }
    val loader = new FXMLLoader(resource)
    val roots = loader.load[AnchorPane]()
    mainLayout.getChildren.setAll(roots)
  }
}
