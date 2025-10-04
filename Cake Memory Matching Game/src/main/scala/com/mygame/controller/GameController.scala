package com.mygame.controller

import javafx.fxml.FXML
import javafx.scene.layout.{AnchorPane, GridPane}
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent
import javafx.animation.{KeyFrame, Timeline}
import javafx.event.{ActionEvent, EventHandler}
import javafx.util.Duration

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

    if (firstClick.isEmpty) {
      firstClick = Some(clickedImage)
      clickedImage.setOpacity(1.0)
    } else if (secondClick.isEmpty && firstClick.get != clickedImage) {
      secondClick = Some(clickedImage)
      clickedImage.setOpacity(1.0)
      checkForMatch()
    }
  }

  protected def checkForMatch(): Unit = {
    // guard just in case
    if (firstClick.isEmpty || secondClick.isEmpty) return

    val firstImageView = firstClick.get
    val secondImageView = secondClick.get

    val firstImageUrl  = Option(firstImageView.getImage).map(_.getUrl).getOrElse("")
    val secondImageUrl = Option(secondImageView.getImage).map(_.getUrl).getOrElse("")

    if (firstImageUrl.nonEmpty && firstImageUrl == secondImageUrl) {
      println("Match found!")
      firstImageView.setOpacity(1.0)
      secondImageView.setOpacity(1.0)

      matchesFound += 1
      println(s"Matches found: $matchesFound")

      if (matchesFound == getTotalPairs) {
        endGame(true)
      }
    } else {
      println("No match found.")
      val timeline = new Timeline(
        new KeyFrame(Duration.seconds(2),
          new EventHandler[ActionEvent] {
            override def handle(e: ActionEvent): Unit = {
              // ensure nodes still exist
              if (firstImageView != null) firstImageView.setOpacity(0.0)
              if (secondImageView != null) secondImageView.setOpacity(0.0)
            }
          }
        )
      )
      timeline.setCycleCount(1)
      timeline.play()
    }

    // reset for next round
    firstClick = None
    secondClick = None
  }

  // Abstract methods — subclasses MUST implement these
  def getTotalPairs: Int
  def endGame(didWin: Boolean): Unit
}
