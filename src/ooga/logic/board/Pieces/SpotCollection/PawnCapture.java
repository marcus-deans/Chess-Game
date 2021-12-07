package ooga.logic.board.Pieces.SpotCollection;

import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

/**
 * Class that stores the possible coordinates a pawn could capture from a given coordinate
 */
public class PawnCapture extends SpotCollection {
  private static final String PIECE_AS_STRING = "pawnCapture";

  public PawnCapture(int width){
    super(width);
  }

  @Override
  public List<List<Coordinate>> getPossibleSpots(Coordinate myCoordinate) {
    List<List<Coordinate>> myList = OneTimePossibleSpots(PIECE_AS_STRING, myCoordinate);

    return myList;
  }
}
