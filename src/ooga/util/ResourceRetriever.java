package ooga.util;

import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.stage.Window;

public class ResourceRetriever {

  //retrieves relevant word from the "words" ResourceBundle
  public static String getWord(String key) {
    ResourceBundle words = ResourceBundle.getBundle("words");
    String value = "error";
    try {
      value = words.getString(key);
    } catch (Exception exception) {
      sendAlert(String.format("%s string was not found in Resource File.", key));
    }
    return value;
  }

  // displays alert/error message to the user - currently duplicated in SharedUIComponents
  private static void sendAlert(String alertMessage) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText(alertMessage);
    alert.show();
  }

//  public static String colorToHexString(Color color) {
//    return "#" + (formatColorToHex(color.getRed()) + formatColorToHex(color.getGreen()) + formatColorToHex(color.getBlue()) + formatColorToHex(color.getOpacity()))
//        .toUpperCase();
//  }
//
//  private static String formatColorToHex(double val) {
//    String in = Integer.toHexString((int) Math.round(val * 255));
//    return in.length() == 1 ? "0" + in : in;
//  }

  // Helper method
  private static String format(double val) {
    String in = Integer.toHexString((int) Math.round(val * 255));
    return in.length() == 1 ? "0" + in : in;
  }

  public static String colorToHexString(Color value) {
    return "#" + (format(value.getRed()) + format(value.getGreen()) + format(value.getBlue()) + format(value.getOpacity()))
        .toUpperCase();
  }

  public static String toHexCode( Color color )
  {
    return String.format( "%02X%02X%02X",
        (int)( color.getRed() * 255 ),
        (int)( color.getGreen() * 255 ),
        (int)( color.getBlue() * 255 ) );
  }

  public static void showAlert(Alert.AlertType alertType,String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.show();
  }
}
