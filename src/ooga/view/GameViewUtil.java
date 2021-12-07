package ooga.view;

import java.util.ResourceBundle;
import javafx.scene.control.Alert;

/**
 * utility class that is used to reduce the length of GameView and remove simple access methods
 * @author marcusdeans
 */
public class GameViewUtil {
  //General resource file structure
  private static final String GAME_VIEW_RESOURCES_FILE_PATH = "ooga.view.viewresources.GameViewResources";
  private static final ResourceBundle gameViewResources = ResourceBundle.getBundle(
      GAME_VIEW_RESOURCES_FILE_PATH);

  // displays alert/error message to the user - currently duplicated in SharedUIComponents
  public static void sendAlert(String alertMessage) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText(alertMessage);
    alert.show();
  }

  //retrieves relevant word from the "words" ResourceBundle
  public static String getWord(String key) {
    ResourceBundle words = ResourceBundle.getBundle("words");
    String value = "error";
    try {
      value = words.getString(key);
    } catch (Exception exception) {
      sendAlert(String.format("%s string was not found in Resource File %s", key,
          GAME_VIEW_RESOURCES_FILE_PATH));
    }
    return value;
  }

  //return the integer from the resource file based on the provided string
  public static int getInt(String key) {
    int value;
    try {
      value = Integer.parseInt(gameViewResources.getString(key));
    } catch (Exception e) {
      value = -1;
    }
    return value;
  }

  //retrieves relevant word from the "words" ResourceBundle
  public static String getString(String key) {
    String value;
    try {
      value = gameViewResources.getString(key);
    } catch (Exception exception) {
      value = "error";
    }
    return value;
  }
}
