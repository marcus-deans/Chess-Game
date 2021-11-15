package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

public class OneTimeDiagonal extends SpotCollection{

  public List<Coordinate> getPossibleSpots(Coordinate coordinate, int xChange, int yChange) {
    List<Coordinate> myCoords = new ArrayList<>();
    myCoords.add(Diagonal(coordinate,xChange,yChange));
    return myCoords;
  }

  @Override
  public List<Coordinate> getPossibleSpots(Coordinate coordinate) {
    return null;
  }
}
