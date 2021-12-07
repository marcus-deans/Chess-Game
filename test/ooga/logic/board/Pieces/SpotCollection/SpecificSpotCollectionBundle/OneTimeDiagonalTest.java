package ooga.logic.board.Pieces.SpotCollection.SpecificSpotCollectionBundle;

import java.util.List;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OneTimeDiagonalTest {
private SpecificSpotCollection mySpot;
private Coordinate innerCoordinate;
private Coordinate outerCoordinate;
  private final int BOARD_SIZE = 8;

  @BeforeEach
  void setUp() {
    mySpot = new OneTimeDirection(BOARD_SIZE);
    innerCoordinate = new GameCoordinate(1,1);
    outerCoordinate = new GameCoordinate(0,0);
  }

  @Test
  void innerCoordinateValid() {
    List<Coordinate> myCoords = mySpot.getPossibleSpots(innerCoordinate,1,1);

    Coordinate sln = new GameCoordinate(2,2);
    assertTrue(myCoords.contains(sln));
  }

  @Test
  void outerCoordinateValid() {
    List<Coordinate> myCoords = mySpot.getPossibleSpots(outerCoordinate,1,1);
    Coordinate sln = new GameCoordinate(1,1);
    assertTrue(myCoords.contains(sln));
  }

  @Test
  void outerCoordinateInvalid() {
    List<Coordinate> myCoords = mySpot.getPossibleSpots(outerCoordinate,-1,-1);
    assertEquals(myCoords.size(), 1);
  }

}