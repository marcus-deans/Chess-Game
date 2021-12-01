package ooga.logic.board.Pieces.PieceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ooga.logic.board.Pieces.PieceCollection.DefaultPromotionPieces;
import ooga.logic.board.Pieces.SpotCollection.LastRankSpots;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GeneralPieceTest {
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