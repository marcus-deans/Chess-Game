package ooga.logic.board.Pieces.PieceBundle;

import static org.junit.jupiter.api.Assertions.*;

import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RookTest {
  private Piece myPiece;
  private Coordinate myCoord;
  @BeforeEach
  void setUp() {
    myPiece = new Rook(2,2,1);
    myCoord = new GameCoordinate(2,2);
  }

  @Test
  void canCapture() {
    myCoord.setCoordinate(3,2);
    assertTrue(myPiece.canCapture(myCoord));
  }

  @Test
  void getMyMovement() {
    myCoord.setCoordinate(2,3);
    assertTrue(myPiece.canMoveTo(myCoord));
    myCoord.setCoordinate(1,1);
    assertFalse(myPiece.canMoveTo(myCoord));
  }

  @Test
  void setMyCapture() {
    myCoord.setCoordinate(4,2);
    assertTrue(myPiece.canCapture(myCoord));
    myCoord.setCoordinate(3,5);
    assertFalse(myPiece.canCapture(myCoord));
  }

}