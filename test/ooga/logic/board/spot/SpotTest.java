package ooga.logic.board.spot;

import ooga.logic.board.Pieces.PieceBundle.Piece;
import ooga.logic.board.coordinate.GameCoordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpotTest {


    @Test
    public void testSpotCoordinates(){
        Piece myPiece = null;
        GameCoordinate testCoordinate = new GameCoordinate(1,1);
        GameSpot myGameSpot = new GameSpot(myPiece,0, 0, 0, true);
        myGameSpot.setCoordinate(1,1);
        assertEquals(testCoordinate,myGameSpot.getCoordinate());
    }
    @Test
    public void testSpotType(){
        Piece myPiece = null;
        GameSpot myGameSpot = new GameSpot(myPiece,0, 0, 0, true);
        //myPiece = new King(1,2,2);
        //myGameSpot.setTypeOfSpot(myPiece);
        //assertEquals(King,myGameSpot.getTypeOfSpot());
    }

}
