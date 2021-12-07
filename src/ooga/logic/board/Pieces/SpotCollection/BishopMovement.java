package ooga.logic.board.Pieces.SpotCollection;

import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

/**
 * Class that stores the possible spots a bishop could reach from a given coordinate
 */
public class BishopMovement extends SpotCollection {
  private static final String PIECE_AS_STRING = "bishop";

  /**
   * Pass width back to define bounds
   * @param width max of width of height of board
   */
  public BishopMovement(int width){
    super(width);
  }

  @Override
  public List<List<Coordinate>> getPossibleSpots(Coordinate myCoordinate) {
    return ContinuousPossibleSpots(PIECE_AS_STRING, myCoordinate);
  }
}
