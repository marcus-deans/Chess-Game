package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

public class BishopMovement extends SpotCollection {
  @Override
  public List<Coordinate> getPossibleSpots(Coordinate myCoordinate) {
    List<Coordinate> myCoords = new ArrayList<>();
    myCoords.addAll(new BottomLeftDiagonal().getPossibleSpots(myCoordinate));
    myCoords.addAll(new BottomRightDiagonal().getPossibleSpots(myCoordinate));
    myCoords.addAll(new TopLeftDiagonal().getPossibleSpots(myCoordinate));
    myCoords.addAll(new TopRightDiagonal().getPossibleSpots(myCoordinate));

    return myCoords;
  }
}