package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

public class BishopMovement extends SpotCollection {
  @Override
  public List<Coordinate> getPossibleSpots(Coordinate myCoordinate) {
    List<Coordinate> myCoords = new ArrayList<>();
    myCoords.addAll(new ContinuousLine().getPossibleSpots(myCoordinate, -1,1));
    myCoords.addAll(new ContinuousLine().getPossibleSpots(myCoordinate,-1,-1));
    myCoords.addAll(new ContinuousLine().getPossibleSpots(myCoordinate,1,-1));
    myCoords.addAll(new ContinuousLine().getPossibleSpots(myCoordinate,1,1));

    return myCoords;
  }
}