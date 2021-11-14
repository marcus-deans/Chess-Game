package ooga.logic.board;

import java.util.List;

public interface Board {

    //returns 2D spot array of the board
    public List<Spot> getFullBoard();
    public void setupBoard(String[][] setup);


}
