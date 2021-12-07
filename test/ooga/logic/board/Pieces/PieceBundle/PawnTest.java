package ooga.logic.board.Pieces.PieceBundle;

import static org.junit.jupiter.api.Assertions.*;

//import ooga.logic.board.Pieces.SpotCollection.LastRankSpots;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PawnTest {
  private Piece myPiece;
  private Coordinate myCoord;
  @BeforeEach
  void setUp() {
    myPiece = new Pawn(2,2,1);
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
    myCoord.setCoordinate(1,1);
    assertFalse(myPiece.canCapture(myCoord));
  }

//
//  @Test
//  void setMyPromotionPieces() {
//    myPiece.setMyPromotionPieces(new DefaultPromotionPieces());
//    assertTrue(myPiece.possiblePromotionPieces().getPossiblePieces().size() == 4);
//  }
//
//  @Test
//  void promotionSquares() {
//    myPiece.setMyPromotionPieces(new DefaultPromotionPieces());
//    myPiece.setMyPromotionSpots(new LastRankSpots(new DefaultPromotionPieces()));
//    assertTrue(myPiece.promotionSquares().getPossibleSpots(myCoord).size() == 8);
//  }
}