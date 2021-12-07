package ooga.view.ui.playerlogin;

import javafx.stage.Stage;
import ooga.view.PanelListener;

/**
 * JavaFX interface allowing for the creation of PlayerLogin capacibilities through modals
 * Relies on appropriate resourcebundles being configured, JavaFX
 *
 * @author marcusdeans
 */
public interface PlayerLoginInterface {

  /**
   * Start the JavaFX application
   * @param primaryStage the JavaFX stage upon which the application will be situated
   */
  void start(Stage primaryStage);

  /**
   * Define the PanelListener for this application which will allow communication
   * @param panelListener the PanelListener instance
   */
  void setPanelListener(PanelListener panelListener);
}
