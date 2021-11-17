package ooga.logic.board.Pieces.SpotCollection;

import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

public interface AcceptsCoordinates{

 void addCoordinates(List<Coordinate> newCoords);

 void removeCoordinates(List<Coordinate> removeCoords);

 void removeAllCoordinates();


}
