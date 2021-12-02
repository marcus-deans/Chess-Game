package ooga.logic.board.Pieces.SpotCollection;

import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

public class KingMovement extends SpotCollection {
  private static final String HORIZONTAL_RANGE = "horizontalRange";
  private static final String VERTICAL_RANGE = "verticalRange";

  @Override
  public List<Coordinate> getPossibleSpots(Coordinate myCoordinate) {
    int[] addXAmount = stringToIntArr(pieceProperties.getString(HORIZONTAL_RANGE));
    int[] addYAmount  = stringToIntArr(pieceProperties.getString(VERTICAL_RANGE));
    return availableSquares(myCoordinate, addXAmount, addYAmount);
  }
}
