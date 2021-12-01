package ooga.logic.board.Pieces.PieceBundle;

import static org.junit.jupiter.api.Assertions.*;

import ooga.logic.board.Pieces.PieceCollection.DefaultPromotionPieces;
import ooga.logic.board.Pieces.SpotCollection.LastRankSpots;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KnightTest {
  private Piece myPiece;
  private Coordinate myCoord;
  @BeforeEach
  void setUp() {
    myPiece = new Knight(2,2,1);
    myCoord = new GameCoordinate(2,2);
  }

  @Test
  void getCoordinate() {
    assertEquals(myPiece.getCoordinate(),myCoord);
  }

  @Test
  void setCoordinate() {
    myCoord.setCoordinate(2,4);
    myPiece.setCoordinate(myCoord);
    assertEquals(myPiece.getCoordinate(),myCoord);
  }

  @Test
  void canCapture() {
    myCoord.setCoordinate(3,4);
    assertTrue(myPiece.canCapture(myCoord));
  }

  @Test
  void getMyMovement() {
    myCoord.setCoordinate(2,3);
    assertFalse(myPiece.getPossibleMoves().getPossibleSpots(myPiece.getCoordinate()).contains(myCoord));
    myCoord.setCoordinate(1,0);
    assertTrue(myPiece.getPossibleMoves().getPossibleSpots(myPiece.getCoordinate()).contains(myCoord));
  }

  @Test
  void getMyCapture() {
    myCoord.setCoordinate(2,4);
    assertFalse(myPiece.getPossibleCaptures().getPossibleSpots(myPiece.getCoordinate()).contains(myCoord));
    myCoord.setCoordinate(0,1);
    assertTrue(myPiece.getPossibleCaptures().getPossibleSpots(myPiece.getCoordinate()).contains(myCoord));
  }


  @Test
  void setAndGetTeam() {
    myPiece.setTeam(3);
    assertEquals(myPiece.getTeam(),3);
  }

  @Test
  void setAndGetValue() {
    myPiece.setValue(9);
    assertEquals(myPiece.getValue(),9);
  }

  @Test
  void setAndGetCanJump() {
    myPiece.setCanJump(true);
    assertTrue(myPiece.getCanJump());
  }

}