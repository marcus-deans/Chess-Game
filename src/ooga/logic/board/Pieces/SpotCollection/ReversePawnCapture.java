package ooga.logic.board.Pieces.SpotCollection;

import java.util.List;

import ooga.logic.board.coordinate.Coordinate;

/**
 * Class that stores the possible coordinates a backwards pawn could capture from a given coordinate
 */
public class ReversePawnCapture extends SpotCollection {
  private static final String PIECE_AS_STRING = "reversePawnCapture";

  /**
   * Pass width back to define bounds
   * @param width max of width of height of board
   */
  public ReversePawnCapture(int width){
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
    return OneTimePossibleSpots(PIECE_AS_STRING, myCoordinate);
  }

}
