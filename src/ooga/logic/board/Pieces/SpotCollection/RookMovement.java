package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.Pieces.SpotCollection.SpecificSpotCollectionBundle.ContinuousLine;
import ooga.logic.board.coordinate.Coordinate;

public class RookMovement extends SpotCollection {
  @Override
  public List<Coordinate> getPossibleSpots(Coordinate myCoordinate) {
    List<Coordinate> myCoords = new ArrayList<>();
    myCoords.addAll(new ContinuousLine().getPossibleSpots(myCoordinate, -1,0));
    myCoords.addAll(new ContinuousLine().getPossibleSpots(myCoordinate,0,-1));
    myCoords.addAll(new ContinuousLine().getPossibleSpots(myCoordinate,0,1));
    myCoords.addAll(new ContinuousLine().getPossibleSpots(myCoordinate,1,0));

    return myCoords;
  }

}