package ooga.logic.board.Pieces.SpotCollection;

import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

public class BishopMovement extends SpotCollection {
  private static final String PIECE_AS_STRING = "bishop";

  public BishopMovement(){
    super();
  }

  @Override
  public List<List<Coordinate>> getPossibleSpots(Coordinate myCoordinate) {
    return ContinuousPossibleSpots(PIECE_AS_STRING, myCoordinate);
  }
}
