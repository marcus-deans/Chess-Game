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
    myCoord.setCoordinate(2,4);
    assertTrue(myPiece.canCapture(myCoord));
  }

  @Test
  void getMyMovement() {
    myCoord.setCoordinate(3,3);
    assertFalse(myPiece.getPossibleMoves().getPossibleSpots(myPiece.getCoordinate()).contains(myCoord));
    myCoord.setCoordinate(2,0);
    assertTrue(myPiece.getPossibleMoves().getPossibleSpots(myPiece.getCoordinate()).contains(myCoord));
  }

  @Test
  void getMyCapture() {
    myCoord.setCoordinate(4,4);
    assertFalse(myPiece.getPossibleCaptures().getPossibleSpots(myPiece.getCoordinate()).contains(myCoord));
    myCoord.setCoordinate(7,2);
    assertTrue(myPiece.getPossibleCaptures().getPossibleSpots(myPiece.getCoordinate()).contains(myCoord));
  }

}