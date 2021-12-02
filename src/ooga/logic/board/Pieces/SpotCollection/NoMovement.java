package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

public class NoMovement extends SpotCollection {

  public NoMovement(){
    super();
  }

  @Override
  public List<Coordinate> getPossibleSpots(Coordinate myCoordinate) {
    return new ArrayList<>();
  }
}