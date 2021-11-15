package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

public class QueenMovement extends SpotCollection {
  @Override
  public List<Coordinate> getPossibleSpots(Coordinate myCoordinate) {
    List<Coordinate> myCoords = new ArrayList<>();
    myCoords.addAll(new RookMovement().getPossibleSpots(myCoordinate));
    myCoords.addAll(new BishopMovement().getPossibleSpots(myCoordinate));
    return myCoords;
  }
}