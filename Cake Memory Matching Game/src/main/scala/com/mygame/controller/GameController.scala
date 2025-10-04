package com.mygame.controller

import scalafxml.core.macros.sfxml
import javafx.fxml.FXML
import javafx.scene.layout.{AnchorPane, GridPane}
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent
import javafx.animation.{KeyFrame, Timeline}
import javafx.util.{Duration => JFXDuration}

abstract class GameController {

  @FXML protected var mainLayout: AnchorPane = _
  @FXML protected var gridPane: GridPane = _

  @FXML protected var imageView1: ImageView = _
  @FXML protected var imageView2: ImageView = _
  @FXML protected var imageView3: ImageView = _
  @FXML protected var imageView4: ImageView = _
  @FXML protected var imageView5: ImageView = _
  @FXML protected var imageView6: ImageView = _
  @FXML protected var imageView7: ImageView = _
  @FXML protected var imageView8: ImageView = _
  @FXML protected var imageView9: ImageView = _
  @FXML protected var imageView10: ImageView = _
  @FXML protected var imageView11: ImageView = _
  @FXML protected var imageView12: ImageView = _
  @FXML protected var imageView13: ImageView = _
  @FXML protected var imageView14: ImageView = _
  @FXML protected var imageView15: ImageView = _
  @FXML protected var imageView16: ImageView = _

  protected var firstClick: Option[ImageView] = None
  protected var secondClick: Option[ImageView] = None
  protected var matchesFound: Int = 0

  @FXML
  def handleImageClick(event: MouseEvent): Unit = {
    val clickedImage = event.getSource.asInstanceOf[ImageView]

    // If it's the first click, store it
    if (firstClick.isEmpty) {
      firstClick = Some(clickedImage)
      clickedImage.setOpacity(1.0)
    } else if (secondClick.isEmpty && firstClick.get != clickedImage) {
      // If it's the second click, store it
      secondClick = Some(clickedImage)
      clickedImage.setOpacity(1.0)
      checkForMatch()
    }
  }

  protected def checkForMatch(): Unit = {
    val firstImageView = firstClick.get
    val secondImageView = secondClick.get

    val firstImageUrl = Option(firstImageView.getImage.impl_getUrl).getOrElse("")
    val secondImageUrl = Option(secondImageView.getImage.impl_getUrl).getOrElse("")

    if (firstImageUrl.nonEmpty && firstImageUrl == secondImageUrl) {
      // Match found, keep images visible
      println("Match found!")
      firstImageView.setOpacity(1.0)
      secondImageView.setOpacity(1.0)

      matchesFound += 1
      println(s"Matches found: $matchesFound")

      // Check if the game is won
      if (matchesFound == getTotalPairs) {
        endGame(didWin = true)
      }
    } else {
      // No match, hide images after 2 seconds
      println("No match found.")
      val timeline = new Timeline(new KeyFrame(JFXDuration.seconds(2), _ => {
        firstImageView.setOpacity(0.0)
        secondImageView.setOpacity(0.0)
      }))
      timeline.setCycleCount(1)
      timeline.play()
    }

    // Reset for the next round
    firstClick = None
    secondClick = None
  }


  def getTotalPairs: Int

  def endGame(didWin: Boolean): Unit
}
