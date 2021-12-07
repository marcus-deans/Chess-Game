package ooga.logic.board.Pieces.SpotCollection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RookMovementTest {
  private SpotCollection mySpotCollection;
  private Coordinate innerCoordinate;
  private Coordinate outerCoordinate;
  private Coordinate topCoordinate;
  private final int BOARD_SIZE = 8;


  @BeforeEach
  void setUp() {
    mySpotCollection = new RookMovement(BOARD_SIZE);
    innerCoordinate = new GameCoordinate(2,2);
    outerCoordinate = new GameCoordinate(0,0);
    topCoordinate = new GameCoordinate(3,7);
  }

  @Test
  void getPossibleSpotsInnerCoordinate() {
    List<List<Coordinate>> myCoords = mySpotCollection.getPossibleSpots(innerCoordinate);
    int size = getSize(myCoords);
    assertTrue(size == BOARD_SIZE * 4);
  }



  @Test
  void getPossibleSpotsOuterCoordinate() {
    List<List<Coordinate>> myCoords = mySpotCollection.getPossibleSpots(outerCoordinate);

    int size = getSize(myCoords);
    assertTrue(size == BOARD_SIZE * 4);
  }

  @Test
  void getPossibleSpotsTopCoordinate() {
    List<List<Coordinate>> myCoords = mySpotCollection.getPossibleSpots(topCoordinate);
    int size = getSize(myCoords);
    assertTrue(size == BOARD_SIZE * 4);
  }

  private int getSize(List<List<Coordinate>> myCoords) {
    int size = 0;
    for (List<Coordinate> x : myCoords){
      size += x.size();
    }
    return size;
  }
}