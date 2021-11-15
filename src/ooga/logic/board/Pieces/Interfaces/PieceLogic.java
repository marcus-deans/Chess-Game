package ooga.logic.board.Pieces.Interfaces;

import ooga.logic.board.coordinate.Coordinate;

//The purpose of this interface is to create a template for the pieces found on the board
//It will interact with the Board class and the Coordinate class
public interface PieceLogic {


    /**
     * Sets the state of the piece, 0 for empty, 1 for pawn, etc.
     * Needed when a piece is taken or a pawn reaches the end
     */
    void setState();

    /**
     * Retrieves the coordinate set for the piece
     */
    Coordinate getCoordinate();

    /**
     * Sets the state of the piece, 0 for empty, 1 for pawn, etc.
     * Needed when a piece is taken or a pawn reaches the end
     */
    void setTeam(int newTeam);

    /**
     * Sets the state of the piece, 0 for empty, 1 for pawn, etc.
     * Needed when a piece is taken or a pawn reaches the end
     */
    int getTeam();

}
