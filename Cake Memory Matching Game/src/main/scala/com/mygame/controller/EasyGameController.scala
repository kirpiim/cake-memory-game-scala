package com.mygame.controller

import scalafxml.core.macros.sfxml
import javafx.scene.input.MouseEvent
import javafx.fxml.FXML


@sfxml
class EasyGameController extends GameController {

  @FXML
  override def handleImageClick(event: MouseEvent): Unit = {
    super.handleImageClick(event) //  to call the gamecontroller class method to handle the click
  }

  override def getTotalPairs: Int = 4

  override def endGame(didWin: Boolean): Unit = {
    if (didWin) {
      println("Congratulations! You won the easy game!")
    } else {
      println("Sorry, you lost the easy game. Better luck next time!")
    }
  }
}
