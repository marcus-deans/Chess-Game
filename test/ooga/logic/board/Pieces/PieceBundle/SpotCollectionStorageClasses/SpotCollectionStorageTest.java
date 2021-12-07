package ooga.logic.board.Pieces.PieceBundle.SpotCollectionStorageClasses;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import ooga.logic.board.Pieces.SpotCollection.BishopMovement;
import ooga.logic.board.Pieces.SpotCollection.PawnCapture;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpotCollectionStorageTest {
  private SpotCollectionStorage myStorage;
  private Map<String,String> myRulesMap;
  private ResourceBundle pieceProperties;
  private ResourceBundle defaultProperties;
  private String teamMatters;
  private static final String PIECES_PACKAGE = "ooga.logic.board.Pieces.PieceBundle.resources.";
  private static final String EMPTY = "";

  @BeforeEach
  void setUp() {
    myRulesMap = new HashMap<>();
    myRulesMap.put("capture", "PawnCapture");
    myRulesMap.put("movement", "RookMovement");

    pieceProperties=ResourceBundle.getBundle(PIECES_PACKAGE+"Bishop");
    defaultProperties= ResourceBundle.getBundle(PIECES_PACKAGE+"Default");
    teamMatters = "";
  }

  @Test
  void getMySpotType() {
    myStorage = new captureStorage("Bishop", myRulesMap,pieceProperties,
        defaultProperties,teamMatters);
    assertEquals(myStorage.getMySpotType(),"capture");
    myStorage = new movementStorage("Bishop", myRulesMap,pieceProperties,
        defaultProperties,teamMatters);
    assertEquals(myStorage.getMySpotType(),"movement");
  }

  @Test
  void getSpotCollection() {
    Coordinate myCoord = new GameCoordinate(3,3);

    myStorage = new captureStorage("Bishop", myRulesMap,pieceProperties,
        defaultProperties,teamMatters);

    assertEquals(getSize(myStorage.getSpotCollection().getPossibleSpots(myCoord)),
        getSize(new PawnCapture(8).getPossibleSpots(myCoord)));

    myRulesMap.remove("capture");

    myStorage.update(myRulesMap);

    assertEquals(getSize(myStorage.getSpotCollection().getPossibleSpots(myCoord)),
        getSize(new BishopMovement(8).getPossibleSpots(myCoord)));


  }

  private int getSize(List<List<Coordinate>> myCoords) {
    int size = 0;
    for (List<Coordinate> x : myCoords){
      size += x.size();
    }
    return size;
  }

}