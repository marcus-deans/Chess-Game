package ooga.logic.board.Pieces.PieceBundle;

import static org.junit.jupiter.api.Assertions.*;

import ooga.logic.board.Pieces.SpotCollection.KnightMovement;
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
    myPiece.setMyMovement(new KnightMovement());
    myCoord.setCoordinate(2,4);
    assertFalse(myPiece.getPossibleMoves().getPossibleSpots(myPiece.getCoordinate()).contains(myCoord));
    myCoord.setCoordinate(1,4);
    assertTrue(myPiece.getPossibleMoves().getPossibleSpots(myPiece.getCoordinate()).contains(myCoord));
  }

  @Test
  void setMyCapture() {
    myPiece.setMyCapture(new KnightMovement());
    myCoord.setCoordinate(2,4);
    assertFalse(myPiece.getPossibleCaptures().getPossibleSpots(myPiece.getCoordinate()).contains(myCoord));
    myCoord.setCoordinate(1,4);
    assertTrue(myPiece.getPossibleCaptures().getPossibleSpots(myPiece.getCoordinate()).contains(myCoord));
  }



}