import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import javafx.scene.layout.AnchorPane

object MyApp extends JFXApp {
  // Specify the resource path for the initial FXML file (usually the main menu)
  val resourcePath = "/com.mygame.view/MainMenu.fxml"
  val rootResource = getClass.getResource(resourcePath)

  println(s"Attempting to load resource: $resourcePath")

  if (rootResource == null) {
    throw new IllegalStateException(s"FXML resource not found: $resourcePath")
  }

  val loader = new FXMLLoader(rootResource, NoDependencyResolver)
  loader.load()

  // Set the root of the scene to your loaded FXML layout
  val roots = loader.getRoot[AnchorPane]

  stage = new PrimaryStage {
    title = "Memory Match Game"
    scene = new Scene(roots)
  }
}
