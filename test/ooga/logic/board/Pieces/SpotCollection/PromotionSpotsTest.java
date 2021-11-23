package ooga.logic.board.Pieces.SpotCollection;

import static org.junit.jupiter.api.Assertions.*;

import ooga.logic.board.Pieces.SpotCollection.FlexibleCoordinateCollection.PromotionSpots;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PromotionSpotsTest {
  private PromotionSpots myPromotionSpots;
  private Coordinate myCoord;
  @BeforeEach
  void setUp() {
    myPromotionSpots = new PromotionSpots();
    myCoord = new GameCoordinate(0,0);
  }

  @Test
  void getPossibleSpotsDefault() {
    assertEquals(myPromotionSpots.getPossibleSpots(myCoord).size(), 8);
    for (int i = 0; i < 8; i++){
      myCoord.setCoordinate(i,7);
      assertTrue(myPromotionSpots.getPossibleSpots(myCoord).contains(myCoord));
    }
    myCoord.setCoordinate(3,3);
    assertFalse(myPromotionSpots.getPossibleSpots(myCoord).contains(myCoord));
  }

  @Test
  void removeAllCoordinates() {
    myPromotionSpots.removeAllCoordinates();
  }

  @Test
  void addCoordinates() {
  }

  @Test
  void removeCoordinates() {
  }

}