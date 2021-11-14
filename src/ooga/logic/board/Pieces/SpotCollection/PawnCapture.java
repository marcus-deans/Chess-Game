package ooga.logic.board.Pieces.SpotCollection;

import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

public class PawnCapture extends SpotCollection {

  public List<Coordinate> getPossibleSpots(Coordinate myCoordinate) {
    int[] addXAmount = new int[]{-1,1};
    int[] addYAmount = new int[]{1};
    return availableSquares(myCoordinate, addXAmount,addYAmount);
  }
}
