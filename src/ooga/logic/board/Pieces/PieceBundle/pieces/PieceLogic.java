package ooga.logic.board.Pieces.PieceBundle.pieces;

import java.util.Map;
import ooga.logic.board.Pieces.PieceCollection.PieceCollection;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;
import ooga.logic.board.coordinate.Coordinate;

/**
 * This interface creates a template of the things that a piece on the board would be able to do,
 * such as functions pertaining to movements, captures, and setting/retrieving information about
 * that piece
 * @author Amr Tagel-Din
 */
public interface PieceLogic {

    /**
     * Retrieves the coordinate point for the piece
     */
    Coordinate getCoordinate();

    /**
     * reset the value of a team
     * @param newTeam team to set a piece
     */
    void setTeam(int newTeam);

    /**
     * @return the team that a piece is on
     */
    int getTeam();

    /**
     * Set the value of a piece
     * @param value new value (like cost, valuableness) of a piece
     */
    void setValue(int value);

    /**
     * @return the value of a piece
     */
    int getValue();

    /**
     * @return if a piece is checkable or not
     */
    boolean getCheckable();

    /**
     * @return if a piece can cannibalize or not
     */
    boolean canCannibalize();

    /**
     * @param myMap new map of rules to update each piece to conform to
     */
    void updateRules(Map<String,String> myMap);

    /**
     * @return The spots you can possibly capture
     */
    SpotCollection getPossibleCaptures();

    /**
     *  can you capture a piece at a given coordinate?
     * @param captureCoordinate the coordinate to be captured
     * @return whether you can capture a piece at the given coordinate
     */
    boolean canCapture(Coordinate captureCoordinate);

    /**
     *
     * @return the list of possible coordinates to move to
     */
    SpotCollection getPossibleMoves();

    /**
     * Changes the coordinate set for the piece
     */
    void setCoordinate(Coordinate passedCoordinate);

    /**
     *
     * @return if a piece can Jump
     */
    boolean getCanJump();

    /**
     * @return the spots in which a piece can promote
     */
    SpotCollection promotionSquares();

    /**
     * @return the pieces that a piece can promote to
     */
    PieceCollection possiblePromotionPieces();

}
