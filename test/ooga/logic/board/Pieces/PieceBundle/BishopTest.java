package ooga.logic.board.Pieces.PieceBundle;

import static org.junit.jupiter.api.Assertions.*;

import ooga.logic.board.Pieces.PieceCollection.DefaultPromotionPieces;
import ooga.logic.board.Pieces.SpotCollection.KnightMovement;
import ooga.logic.board.Pieces.SpotCollection.LastRankSpots;
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

  @Test
  void setMyPromotionPieces() {
    myPiece.setMyPromotionPieces(new DefaultPromotionPieces());
    assertTrue(myPiece.possiblePromotionPieces().getPossiblePieces().size() == 4);
  }

  @Test
  void promotionSquares() {
    myPiece.setMyPromotionPieces(new DefaultPromotionPieces());
    myPiece.setMyPromotionSpots(new LastRankSpots(new DefaultPromotionPieces()));
    assertTrue(myPiece.promotionSquares().getPossibleSpots(myCoord).size() == 8);
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