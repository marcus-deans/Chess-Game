package ooga.view;

import javafx.scene.paint.Color;

public interface PanelListener {
    void updateLanguage(String newLanguage);

    void resetGame();

    void updateColorScheme(Color newColor);

    void loadNewFile(String filename);

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
