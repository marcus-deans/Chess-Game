package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

public class QueenMovement extends SpotCollection {
  @Override
  public List<Coordinate> getPossibleSpots(Coordinate myCoordinate) {
//    List<SpotCollection> myTemplatesToCombine = new ArrayList<>();
//    myTemplatesToCombine.add(new RookMovement());
//    myTemplatesToCombine.add(new BishopMovement());
    List<Coordinate> myCoords = new ArrayList<>();
    myCoords.addAll(new RookMovement().getPossibleSpots(myCoordinate));
    myCoords.addAll(new BishopMovement().getPossibleSpots(myCoordinate));
    return myCoords;
  }
}