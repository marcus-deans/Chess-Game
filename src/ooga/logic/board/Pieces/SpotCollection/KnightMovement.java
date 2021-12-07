package ooga.logic.board.Pieces.SpotCollection;

import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

/**
 * Class that stores the possible coordinates a Knight could reach from a given coordinate
 */
public class KnightMovement extends SpotCollection {
  private static final String PIECE_AS_STRING = "knight";
  private static final String SIGNS_AS_STRING = "signs";

  /**
   * Pass width back to define bounds
   * @param width max of width of height of board
   */
  public KnightMovement(int width){
    super(width);
  }

  @Override
  public List<List<Coordinate>> getPossibleSpots(Coordinate myCoordinate) {
    String[] myDirections = getPieceProperties().getString(PIECE_AS_STRING).split(",");
    int[] signs = stringToIntArr(getPieceProperties().getString(SIGNS_AS_STRING));
    return doubleSymmetricOver(signs,myCoordinate,myDirections);
  }



}
