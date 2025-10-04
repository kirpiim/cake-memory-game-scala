package com.mygame.controller

import scalafx.Includes.{getClass, _}
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import scalafxml.core.macros.sfxml
import javafx.scene.layout.AnchorPane
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.event.ActionEvent

@sfxml
class MainController(
                      @FXML private var mainLayout: AnchorPane
                    ) {

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

  def showGameDifficulty(): Unit = {
    val resource = getClass.getResource("/com.mygame.view/GameDifficulty.fxml")
    if (resource == null) {
      throw new IllegalStateException("FXML resource not found: /com.mygame.view/GameDifficulty.fxml")
    }

    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()

    val roots = loader.getRoot[AnchorPane]
    if (roots == null) {
      throw new IllegalStateException("Failed to load GameDifficulty.fxml")
    }

    mainLayout.getChildren.setAll(roots)
  }

  def showRules(): Unit = {
    val resource = getClass.getResource("/com.mygame.view/Rules.fxml")
    if (resource == null) {
      throw new IllegalStateException("FXML resource not found: /com.mygame.view/Rules.fxml")
    }

    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()

    val roots = loader.getRoot[AnchorPane]
    if (roots == null) {
      throw new IllegalStateException("Failed to load Rules.fxml")
    }

    mainLayout.getChildren.setAll(roots)
  }
  def showMainPage(): Unit = {
    val resource = getClass.getResource("/com.mygame.view/MainMenu.fxml")
    if (resource == null) {
      throw new IllegalStateException("FXML resource not found: /com.mygame.view/MainMenu.fxml")
    }

    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()

    val roots = loader.getRoot[AnchorPane]
    if (roots == null) {
      throw new IllegalStateException("Failed to load MainMenu.fxml")
    }

    mainLayout.getChildren.clear()
    mainLayout.getStylesheets.clear()
    mainLayout.getChildren.setAll(roots)
 }
}







