package ooga.logic.board.Pieces.Interfaces;

import java.util.Map;
import ooga.logic.board.Pieces.PieceCollection.PieceCollection;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;
import ooga.logic.board.coordinate.Coordinate;

//The purpose of this interface is to create a template for the pieces found on the board
//It will interact with the Board class and the Coordinate class
public interface PieceLogic {


    /**
     * Retrieves the coordinate set for the piece
     */
    Coordinate getCoordinate();


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
