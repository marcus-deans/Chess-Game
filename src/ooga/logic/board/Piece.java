package ooga.logic.board;

import ooga.logic.board.coordinate.Coordinate;

import java.util.List;
//The purpose of this interface is to create a template for the pieces found on the board
//It will interact with the Board class and the Coordinate class
public interface Piece {
    /**
     * This method would define the movement of the individual piece. For example,
     * for a pawn it would change the y-position of its location by 1
     */
    public void move();

    /**
     * using the definition of the move method for the specific peice
     * and the piece's current position, it would create a list of
     * possible coordinates the piece could move to
     */
    public void updatePossibleMoves();


    /**
     *
     * @return the list of possible coordinates to move to
     */
    public List<Coordinate> getPossibleMoves();

    /**
     * Sets the state of the piece, 0 for empty, 1 for pawn, etc.
     * Needed when a piece is taken or a pawn reaches the end
     */
    public void setState();

    /**
     * Updates the internal coordinate of the piece after it is moved
     */
    public void updatePosition();

    /**
     * Changes the coordinate set for the piece
     */
    public void setCoordinate();

    /**
     * Retrieves the coordinate set for the piece
     */
    public Coordinate getCoordinate();
}
