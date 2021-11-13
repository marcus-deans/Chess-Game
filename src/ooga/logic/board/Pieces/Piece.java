package ooga.logic.board.Pieces;

import java.util.List;
import ooga.logic.board.Coordinate;

//The purpose of this interface is to create a template for the pieces found on the board
//It will interact with the Board class and the Coordinate class
public interface Piece {


    /**
     * Sets the state of the piece, 0 for empty, 1 for pawn, etc.
     * Needed when a piece is taken or a pawn reaches the end
     */
    public void setState();

    /**
     * Retrieves the coordinate set for the piece
     */
    public Coordinate getCoordinate();

    public void remove();
}
