package com.mygame.controller

import javafx.fxml.FXML
import javafx.scene.input.MouseEvent

class HardGameController extends GameController {

  @FXML
  override def handleImageClick(event: MouseEvent): Unit = {
    super.handleImageClick(event)
  }

  override def getTotalPairs: Int = 8

  override def endGame(didWin: Boolean): Unit = {
    if (didWin) println("Congratulations! You won the hard game!")
    else println("The hard game got the best of you. Try again!")
  }
}
