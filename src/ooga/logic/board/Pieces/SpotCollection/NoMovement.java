package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

/**
 * No movement; used for objects that reach nothing, to avoid nulls
 */
public class NoMovement extends SpotCollection {

  /**
   * Pass width back to define bounds
   * @param width max of width of height of board
   */
  public NoMovement(int width){
    super(width);
  }

  @Override
  public List<List<Coordinate>> getPossibleSpots(Coordinate myCoordinate) {
    return new ArrayList<>();
  }
}