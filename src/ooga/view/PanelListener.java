package ooga.view;

import com.opencsv.exceptions.CsvValidationException;
import javafx.scene.paint.Color;
import ooga.util.IncorrectCSVFormatException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface PanelListener {
    void updateLanguage(String newLanguage);

    void resetGame();

    void updateColorScheme(Color newColor);

    void loadNewFile(String filename) throws CsvValidationException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IncorrectCSVFormatException;

    void saveCurrentFile();

    /**
     * Redo a move that was previously undone
     */
    void redoMove();

    /**
     * Undo the previous move
     */
    void undoMove();

    void changeVariant(String variant);

    void getBoardClick(int x, int y);
}
