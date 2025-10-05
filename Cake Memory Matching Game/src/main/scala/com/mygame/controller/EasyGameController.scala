package com.mygame.controller

import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage

class EasyGameController extends GameController {

  @FXML
  def handleImageClick(event: MouseEvent): Unit = {
    val imageView = event.getSource.asInstanceOf[ImageView]
    super.handleImageClick(imageView)
  }

  override def getTotalPairs: Int = 4

  override def endGame(didWin: Boolean): Unit = {
    if (didWin) {
      try {
        val resource = getClass.getResource("/com/mygame/view/YouWon.fxml")
        val loader = new FXMLLoader(resource)
        val root = loader.load[AnchorPane]()
        val stage = gridPane.getScene.getWindow.asInstanceOf[Stage]
        stage.getScene.setRoot(root)
      } catch {
        case e: Exception =>
          e.printStackTrace()
      }
    } else {
      println("Game lost! Try again.")
    }
  }
}
