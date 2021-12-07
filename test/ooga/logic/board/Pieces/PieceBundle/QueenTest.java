package ooga.logic.board.Pieces.PieceBundle;

import static org.junit.jupiter.api.Assertions.*;

import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QueenTest {
  private Piece myPiece;
  private Coordinate myCoord;
  @BeforeEach
  void setUp() {
    myPiece = new Queen(2,2,1);
    myCoord = new GameCoordinate(2,2);
  }

  @Test
  void canCapture() {
    myCoord.setCoordinate(3,3);
    assertTrue(myPiece.canCapture(myCoord));
  }

  @Test
  void getMyMovement() {
    myCoord.setCoordinate(2,3);
    assertTrue(myPiece.canMoveTo(myCoord));
    myCoord.setCoordinate(1,0);
    assertFalse(myPiece.canMoveTo(myCoord));  }

  @Test
  void setMyCapture() {
    myCoord.setCoordinate(1,3);
    assertTrue(myPiece.canCapture(myCoord));
    myCoord.setCoordinate(1,7);
    assertFalse(myPiece.canCapture(myCoord));
  }


}