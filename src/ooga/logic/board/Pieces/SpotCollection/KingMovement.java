package ooga.logic.board.Pieces.SpotCollection;

import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

/**
 * Class that stores the possible coordinates a King could reach from a given coordinate
 */
public class KingMovement extends SpotCollection {
  private static final String HORIZONTAL_RANGE = "horizontalRange";
  private static final String VERTICAL_RANGE = "verticalRange";

  /**
   * Pass width back to define bounds
   * @param width max of width of height of board
   */
  public KingMovement(int width){
    super(width);
  }

  /**
   * return a list of list of coordinates, where within each list a coordinate being reached
   * depends on the coordinate before it
   * @param myCoordinate the coordinate we are currently at
   * @return the coordinates we could reach with no regard to bounds
   */
  @Override
  public List<List<Coordinate>> getPossibleSpots(Coordinate myCoordinate) {
    int[] addXAmount = stringToIntArr(getPieceProperties().getString(HORIZONTAL_RANGE));
    int[] addYAmount  = stringToIntArr(getPieceProperties().getString(VERTICAL_RANGE));
    return availableSquares(myCoordinate, addXAmount, addYAmount);
  }
}
