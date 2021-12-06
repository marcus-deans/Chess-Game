package ooga.view;

import javafx.scene.layout.GridPane;
import ooga.logic.board.spot.Spot;

public interface GridChessView {
    void update(int row, int column, int state);

    void updateChessCell(Spot spot);

    void colourChessCell(Spot spot, String hexColour);

    GridPane getMyGameGrid();
}
