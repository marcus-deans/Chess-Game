package ooga.logic.board.Pieces.SpotCollection.SpecificSpotCollectionBundle;

import java.util.List;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContinuousLineTest {

  private SpecificSpotCollection mySpot;
  private Coordinate innerCoordinate;
  private Coordinate outerCoordinate;
  private final int BOARD_SIZE = 8;

  @BeforeEach
  void setUp() {
    mySpot = new ContinuousLine(BOARD_SIZE);
    innerCoordinate = new GameCoordinate(2,2);
    outerCoordinate = new GameCoordinate(0,0);
  }

  @Test
  void outerCoordinateGoingOutwards() {
    List<Coordinate> myCoords = mySpot.getPossibleSpots(outerCoordinate,-1,-1);
    assertTrue(myCoords.size() == BOARD_SIZE);
  }

  @Test
  void innerCoordinateGoingOutwards() {
    List<Coordinate> myCoords = mySpot.getPossibleSpots(innerCoordinate,-1,-1);
    System.out.println(myCoords.size());
    assertTrue(myCoords.size() == BOARD_SIZE);
  }

}