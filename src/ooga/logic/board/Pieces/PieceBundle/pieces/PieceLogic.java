package ooga.logic.board.Pieces.PieceBundle.pieces;

import java.util.Map;
import ooga.logic.board.Pieces.PieceCollection.PieceCollection;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;
import ooga.logic.board.coordinate.Coordinate;

/**
 * This interface creates a template of the things that a piece on the board would be able to do,
 * such as functions pertaining to movements, captures, and setting/retrieving information about
 * that piece
 *
 * @author Amr Tagel-Din
 */
public interface PieceLogic {

    /**
     * Retrieves the coordinate point for the piece
     */
    Coordinate getCoordinate();

    /**
     *
     * @param newTeam
     */
    void setTeam(int newTeam);


    int getTeam();

    void setValue(int value);

    int getValue();

    boolean getCheckable();

    boolean canCannibalize();

    void updateRules(Map<String,String> myMap);


    SpotCollection getPossibleCaptures();

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


    boolean getCanJump();


    SpotCollection promotionSquares();

    PieceCollection possiblePromotionPieces();

}
