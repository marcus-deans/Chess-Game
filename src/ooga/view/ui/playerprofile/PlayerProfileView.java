package ooga.view.ui.playerprofile;

import javafx.application.Application;
import javafx.stage.Stage;
import ooga.logic.game.Player;
import ooga.view.PanelListener;

public class PlayerProfileView extends Application implements PlayerProfileInterface{
  PanelListener myPanelListener;
  Player myPlayer;

  public PlayerProfileView(Player player){
    myPlayer = player;
  }

  /**
   * The main entry point for all JavaFX applications. The start method is called after the init
   * method has returned, and after the system is ready for the application to begin running.
   *
   * <p>
   * NOTE: This method is called on the JavaFX Application Thread.
   * </p>
   *
   * @param primaryStage the primary stage for this application, onto which the application scene
   *                     can be set. Applications may create other stages, if needed, but they will
   *                     not be primary stages.
   */
  @Override
  public void start(Stage primaryStage) {

  }

  @Override
  public void setPanelListener(PanelListener panelListener) {
    myPanelListener = panelListener;
  }
}
