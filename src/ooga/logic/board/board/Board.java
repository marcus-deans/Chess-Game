package ooga.logic.board.board;

import ooga.logic.board.spot.Spot;

import java.util.List;

public interface Board {

    //returns 2D spot array of the board
    public List<Spot> getFullBoard();
    public void setupBoard(String[][] setup);


}
