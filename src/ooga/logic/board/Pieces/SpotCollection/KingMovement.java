package ooga.logic.board.Pieces.SpotCollection;

import java.util.List;
import ooga.logic.board.Coordinate;

public class KingMovement extends SpotCollection {

  public List<Coordinate> getPossibleSpots(Coordinate myCoordinate) {
    int[] myXInts = new int[]{-1,0,1};
    int[] myYInts = new int[]{-1,0,1};
    return availableSquares(myCoordinate, myXInts, myYInts);
  }
}