package ooga.logic.board.Pieces.SpotCollection;

import java.util.List;

import ooga.logic.board.coordinate.Coordinate;

public class ReversePawnMovement extends SpotCollection {
  private static final String PIECE_AS_STRING = "reversePawnMovement";

  @Override
  public List<Coordinate> getPossibleSpots(Coordinate myCoordinate) {
    return OneTimePossibleSpots(PIECE_AS_STRING, myCoordinate);
  }

}
