package ooga.view.ui.playerlogin;

import javafx.stage.Stage;
import ooga.view.PanelListener;

public interface PlayerLoginInterface {
  void start(Stage primaryStage);

  void setPanelListener(PanelListener panelListener);
}
