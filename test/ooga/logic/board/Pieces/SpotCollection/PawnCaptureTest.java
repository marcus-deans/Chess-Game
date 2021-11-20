package ooga.logic.board.Pieces.SpotCollection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import ooga.logic.board.Pieces.SpotCollection.SpecificSpotCollectionBundle.ContinuousLine;
import ooga.logic.board.Pieces.SpotCollection.SpecificSpotCollectionBundle.SpecificSpotCollection;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PawnCaptureTest {
  private SpotCollection mySpotCollection;
  private Coordinate innerCoordinate;
  private Coordinate outerCoordinate;
  private Coordinate topCoordinate;

  @BeforeEach
  void setUp() {
    mySpotCollection = new PawnCapture();
    innerCoordinate = new GameCoordinate(2,2);
    outerCoordinate = new GameCoordinate(0,0);
    topCoordinate = new GameCoordinate(3,7);
  }

  @Test
  void getPossibleSpotsInnerCoordinate() {
    List<Coordinate> myCoords = mySpotCollection.getPossibleSpots(innerCoordinate);
//    Coordinate myCoord1 = new GameCoordinate(1,3);
//    Coordinate myCoord2 = new GameCoordinate(3,3);
    assertTrue(myCoords.size() == 2);
  }

  @Test
  void getPossibleSpotsOuterCoordinate() {
    List<Coordinate> myCoords = mySpotCollection.getPossibleSpots(outerCoordinate);
//    Coordinate myCoord1 = new GameCoordinate(1,3);
//    Coordinate myCoord2 = new GameCoordinate(3,3);
    assertTrue(myCoords.size() == 1);
  }

  @Test
  void getPossibleSpotsTopCoordinate() {
    List<Coordinate> myCoords = mySpotCollection.getPossibleSpots(topCoordinate);
//    Coordinate myCoord1 = new GameCoordinate(1,3);
//    Coordinate myCoord2 = new GameCoordinate(3,3);
    assertTrue(myCoords.size() == 0);
  }
}