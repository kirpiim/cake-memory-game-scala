package com.mygame.controller

import javafx.fxml.{FXML, Initializable}
import javafx.scene.image.{Image, ImageView}
import javafx.scene.control.Label
import javafx.scene.layout.GridPane
import javafx.scene.input.MouseEvent
import javafx.animation.PauseTransition
import javafx.util.Duration
import scala.util.Random
import java.net.URL
import java.util.ResourceBundle
import scala.collection.mutable.ListBuffer

abstract class GameController extends Initializable {

  @FXML protected var gridPane: GridPane = _
  @FXML private var timerLabel: Label = _

  private var images = ListBuffer[Image]()
  private var imageViews = ListBuffer[ImageView]()
  private var firstSelected: ImageView = null
  private var secondSelected: ImageView = null
  private var matchedCount = 0

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    val totalPairs = getTotalPairs
    val basePath = "/"

    // ✅ Load sweet images
    val loaded = (1 to totalPairs).flatMap { i =>
      val url = getClass.getResource(s"${basePath}sweet$i.png")
      if (url == null) {
        println(s"⚠️ Missing image: sweet$i.png — check path!")
        None
      } else Some(new Image(url.toExternalForm))
    }

    // ✅ If this is easy mode (3×3), add extra.png
    val isEasy = totalPairs == 4
    val finalImages =
      if (isEasy) {
        val extraUrl = getClass.getResource(s"${basePath}extra.png")
        if (extraUrl != null) {
          println("✅ Loaded extra.png for Easy mode.")
          loaded ++ Seq(new Image(extraUrl.toExternalForm))
        } else loaded
      } else loaded

    // ✅ Duplicate pairs and shuffle
    images.clear()
    images ++= (loaded ++ loaded)
    images = Random.shuffle(images)

    // ✅ Choose layout dynamically
    val (cols, rows, fitWidth, fitHeight) =
      if (isEasy) (3, 3, 199.0, 140.0) else (4, 4, 140.0, 100.0)

    gridPane.getChildren.clear()
    imageViews.clear()

    for (i <- images.indices) {
      val imgView = new ImageView(images(i))
      imgView.setFitWidth(fitWidth)
      imgView.setFitHeight(fitHeight)
      imgView.setPreserveRatio(false)
      imgView.setOpacity(0)
      imgView.setOnMouseClicked((_: MouseEvent) => handleImageClick(imgView))
      imageViews += imgView

      val row = i / cols
      val col = i % cols
      gridPane.add(imgView, col, row)
    }

    println(s"✅ Loaded $totalPairs pairs, layout: ${cols}x${rows}.")
  }

  // --- Handle clicks ---
  def handleImageClick(imgView: ImageView): Unit = {
    if (imgView.getOpacity == 1.0 || secondSelected != null) return

    imgView.setOpacity(1.0)

    if (firstSelected == null) firstSelected = imgView
    else {
      secondSelected = imgView
      checkForMatch()
    }
  }

  private def checkForMatch(): Unit = {
    if (firstSelected.getImage == secondSelected.getImage) {
      matchedCount += 1
      firstSelected = null
      secondSelected = null

      // ✅ Stop at correct number of pairs
      if (matchedCount == getTotalPairs) {
        endGame(true)
      }
    } else {
      val pause = new PauseTransition(Duration.seconds(0.8))
      pause.setOnFinished(_ => {
        firstSelected.setOpacity(0)
        secondSelected.setOpacity(0)
        firstSelected = null
        secondSelected = null
      })
      pause.play()
    }
  }

  // --- Abstract methods (already in your subclass) ---
  def getTotalPairs: Int
  def endGame(didWin: Boolean): Unit
}
