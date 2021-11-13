package ooga.view;

import javafx.scene.paint.Color;

public interface PanelListener {
    void updateLanguage(String newLanguage);

    void resetGame();

    void updateColorScheme(Color newColor);

    void loadNewFile(String filename);

    void saveCurrentFile();
}
