package ooga.logic.board.Pieces.SpotCollection;

import java.util.List;

import ooga.logic.board.coordinate.Coordinate;

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
