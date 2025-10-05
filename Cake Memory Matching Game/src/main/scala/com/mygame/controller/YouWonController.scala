package com.mygame.controller

import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.Button
import javafx.scene.layout.AnchorPane
import javafx.event.ActionEvent

class YouWonController {

  @FXML private var mainLayout: AnchorPane = _
  @FXML private var returnButton: Button = _

  @FXML
  def handleReturnButton(event: ActionEvent): Unit = {
    val resource = getClass.getResource("/com/mygame/view/MainMenu.fxml")
    val loader = new FXMLLoader(resource)
    val root = loader.load[AnchorPane]()
    mainLayout.getChildren.setAll(root)
  }
}
