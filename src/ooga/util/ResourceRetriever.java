package ooga.util;

import javafx.scene.control.Alert;

import java.util.ResourceBundle;

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
}
