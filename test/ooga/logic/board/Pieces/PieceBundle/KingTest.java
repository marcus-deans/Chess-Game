package ooga.logic.board.Pieces.PieceBundle;

import static org.junit.jupiter.api.Assertions.*;

//import ooga.logic.board.Pieces.SpotCollection.LastRankSpots;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KingTest {
  private Piece myPiece;
  private Coordinate myCoord;
  @BeforeEach
  void setUp() {
    myPiece = new King(0,2,1);
    myCoord = new GameCoordinate(0,2);
  }

  @Test
  void canCapture() {
    myCoord.setCoordinate(0,3);
    assertTrue(myPiece.canCapture(myCoord));
  }

  @Test
  void getMyMovement() {
    myCoord.setCoordinate(2,4);
    assertFalse(myPiece.canMoveTo(myCoord));
    myCoord.setCoordinate(0,3);
    assertTrue(myPiece.canMoveTo(myCoord));  }

  @Test
  void setMyCapture() {
    myCoord.setCoordinate(0,1);
    assertTrue(myPiece.canCapture(myCoord));
    myCoord.setCoordinate(2,4);
    assertFalse(myPiece.canCapture(myCoord));
  }


}