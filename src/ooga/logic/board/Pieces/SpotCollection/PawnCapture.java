package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

public class PawnCapture extends SpotCollection {

  @Override
  public List<Coordinate> getPossibleSpots(Coordinate myCoordinate) {
    List<Integer> addXAmount = Arrays.asList(-1,1);
    List<Integer> addYAmount  = Arrays.asList(1);
    return availableSquares(myCoordinate, addXAmount,addYAmount);
  }
}
