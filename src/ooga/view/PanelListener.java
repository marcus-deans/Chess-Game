package ooga.view;

import com.opencsv.exceptions.CsvValidationException;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.util.IncorrectCSVFormatException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface PanelListener {
    void updateLanguage(String newLanguage);

    void resetGame();

    void updateColorScheme(String newColor);

    void loadNewFile(String filename) throws CsvValidationException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IncorrectCSVFormatException;

    void saveCurrentFile();

    /**
     * Undo the previous move
     */
    void undoMove();

    void changeVariant(String variant);

    void getBoardClick(int x, int y);

    void openPlayerLogin();

    void setNewPlayer(String username, String email, String password, int team);

    void closePlayerLogin(Stage stage);
}
