package ooga.logic.board.Pieces.PieceBundle;

import static org.junit.jupiter.api.Assertions.*;

import ooga.logic.board.Pieces.PieceCollection.DefaultPromotionPieces;
import ooga.logic.board.Pieces.SpotCollection.LastRankSpots;
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
    myCoord.setCoordinate(3,3);
    assertFalse(myPiece.getPossibleMoves().getPossibleSpots(myPiece.getCoordinate()).contains(myCoord));
    myCoord.setCoordinate(2,3);
    assertTrue(myPiece.getPossibleMoves().getPossibleSpots(myPiece.getCoordinate()).contains(myCoord));
  }

  @Test
  void getMyCapture() {
    myCoord.setCoordinate(2,3);
    assertFalse(myPiece.getPossibleCaptures().getPossibleSpots(myPiece.getCoordinate()).contains(myCoord));
    myCoord.setCoordinate(1,3);
    assertTrue(myPiece.getPossibleCaptures().getPossibleSpots(myPiece.getCoordinate()).contains(myCoord));
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