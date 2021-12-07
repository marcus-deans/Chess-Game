package ooga.logic.board.Pieces.PieceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import ooga.logic.board.Pieces.SpotCollection.LastRankSpots;
import ooga.logic.board.Pieces.SpotCollection.PawnCapture;
import ooga.logic.board.Pieces.SpotCollection.PawnMovement;
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
//    myPiece.setCanJump(true);
    assertFalse(myPiece.getCanJump());
  }

  @Test
  void update(){
    Map<String,String> myMap = new HashMap<>();
    myMap.put("canCannibalize", "true");
    myMap.put("isCheckable", "true");
    myMap.put("jump", "true");
    myMap.put("teamMatters", "true");
    myMap.put("capture", "PawnCapture");
    myMap.put("movement", "PawnMovement");

    Coordinate myCoord = new GameCoordinate(2,2);
    myPiece.updateRules(myMap);
    assertTrue(myPiece.getCanJump());
    assertTrue(myPiece.getCheckable());
    assertTrue(myPiece.canCannibalize());
    assertEquals(getSize(myPiece.getPossibleCaptures().getPossibleSpots(myCoord)),
        getSize(new PawnCapture(8).getPossibleSpots(myCoord)));
    assertEquals(getSize(myPiece.getPossibleMoves().getPossibleSpots(myCoord)),
        getSize(new PawnMovement(8).getPossibleSpots(myCoord)));

  }
  private int getSize(List<List<Coordinate>> myCoords) {
    int size = 0;
    for (List<Coordinate> x : myCoords){
      size += x.size();
    }
    return size;
  }
}