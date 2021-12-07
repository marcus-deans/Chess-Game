package ooga.view.ui.playerprofile;

import javafx.stage.Stage;
import ooga.view.PanelListener;

public interface PlayerProfileInterface {
  void start(Stage primaryStage);

  void setPanelListener(PanelListener panelListener);
}
