package ooga.logic.board.Pieces.SpotCollection;

import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

/**
 * Class that stores the possible coordinates a pawn could capture from a given coordinate
 */
public class PawnCapture extends SpotCollection {
  private static final String PIECE_AS_STRING = "pawnCapture";

  /**
   * Pass width back to define bounds
   * @param width max of width of height of board
   */
  public PawnCapture(int width){
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
    List<List<Coordinate>> myList = OneTimePossibleSpots(PIECE_AS_STRING, myCoordinate);

    return myList;
  }
}
