package com.mygame.controller

import javafx.fxml.FXML
import javafx.scene.input.MouseEvent

class EasyGameController extends GameController {

  @FXML
  override def handleImageClick(event: MouseEvent): Unit = {
    super.handleImageClick(event)
  }

  override def getTotalPairs: Int = 4

  override def endGame(didWin: Boolean): Unit = {
    if (didWin) println("Congratulations! You won the easy game!")
    else println("Sorry, you lost the easy game. Better luck next time!")
  }
}
