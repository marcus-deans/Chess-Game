package ooga.logic.board.Pieces.PieceBundle;

import static org.junit.jupiter.api.Assertions.*;

import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BishopTest {
  private Piece myPiece;
  private Coordinate myCoord;
  @BeforeEach
  void setUp() {
    myPiece = new Bishop(0,2,1);
    myCoord = new GameCoordinate(0,2);
  }


  @Test
  void canCapture() {
    myCoord.setCoordinate(2,4);
    assertTrue(myPiece.canCapture(myCoord));
  }

  @Test
  void setMyMovement() {
    myCoord.setCoordinate(2,4);
    assertTrue(myPiece.canMoveTo(myCoord));
    myCoord.setCoordinate(1,4);
    assertFalse(myPiece.canMoveTo(myCoord));
  }

  @Test
  void setMyCapture() {
    myCoord.setCoordinate(2,4);
    assertTrue(myPiece.canCapture(myCoord));
    myCoord.setCoordinate(1,4);
    assertFalse(myPiece.canCapture(myCoord));
  }



}