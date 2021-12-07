package ooga.logic.board.Pieces.SpotCollection;

import java.util.List;

import ooga.logic.board.coordinate.Coordinate;

/**
 * Class that stores the possible coordinates a backwards pawn could capture from a given coordinate
 */
public class ReversePawnCapture extends SpotCollection {
  private static final String PIECE_AS_STRING = "reversePawnCapture";

  public ReversePawnCapture(int width){
    super(width);
  }

  @Override
  public List<List<Coordinate>> getPossibleSpots(Coordinate myCoordinate) {
    return OneTimePossibleSpots(PIECE_AS_STRING, myCoordinate);
  }

}
