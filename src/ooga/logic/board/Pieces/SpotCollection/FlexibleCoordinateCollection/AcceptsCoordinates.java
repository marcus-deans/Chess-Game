package ooga.logic.board.Pieces.SpotCollection.FlexibleCoordinateCollection;

import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

public interface AcceptsCoordinates{

 public void addCoordinates(List<Coordinate> newCoords);

 void removeCoordinates(List<Coordinate> removeCoords);

 public void removeAllCoordinates();


}
