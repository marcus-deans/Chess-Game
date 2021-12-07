package ooga.view;

import javafx.scene.layout.GridPane;
import ooga.logic.board.spot.Spot;

/**
 * Interface to interact with the game grid of the program, serves as API
 * Used by GameView at length
 * @author marcusdeans
 */
public interface GridChessView {
    void update(int row, int column, int state);

    void updateChessCell(Spot spot);

    void colourChessCell(Spot spot, String hexColour);

    GridPane getMyGameGrid();
}
