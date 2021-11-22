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
    myCoord.setCoordinate(2,4);
    assertTrue(myPiece.canCapture(myCoord));
  }


  @Test
  void possiblePromotionPieces() {
  }

  @Test
  void getPossibleCaptures() {
  }

  @Test
  void getPossibleMoves() {
    myCoord.setCoordinate(2,4);
    assertTrue(myPiece.getPossibleMoves().contains(myCoord));
  }

  @Test
  void setMyMovement() {
    myPiece.setMyMovement(new KnightMovement());
    myCoord.setCoordinate(2,4);
    assertFalse(myPiece.getPossibleMoves().contains(myCoord));
    myCoord.setCoordinate(1,4);
    assertTrue(myPiece.getPossibleMoves().contains(myCoord));
  }

  @Test
  void setMyCapture() {
  }

  @Test
  void setMyPromotionSpots() {
  }

  @Test
  void setMyPromotionPieces() {
  }

  @Test
  void promotionSquares() {
  }

  @Test
  void setTeam() {
    myPiece.setTeam(3);
    assertEquals(myPiece.getTeam(),3);
  }

  @Test
  void getTeam() {
    assertEquals(myPiece.getTeam(),1);
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