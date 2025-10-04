package com.mygame.controller

import scalafxml.core.macros.sfxml
import javafx.scene.input.MouseEvent
import javafx.fxml.FXML

@sfxml
class HardGameController extends GameController {

  @FXML
  override def handleImageClick(event: MouseEvent): Unit = {
    super.handleImageClick(event) // to call the gamecontroller class method to handle the click
  }

  override def getTotalPairs: Int = 8

  override def endGame(didWin: Boolean): Unit = {
    if (didWin) {
      println("Congratulations! You won the hard game!")
    } else {
      println("The hard game got the best of you. Try again!")
    }
  }
}
