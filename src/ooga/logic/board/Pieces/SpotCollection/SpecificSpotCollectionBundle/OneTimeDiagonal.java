package ooga.logic.board.Pieces.SpotCollection.SpecificSpotCollectionBundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

public class OneTimeDiagonal extends SpecificSpotCollection {

  public List<Coordinate> getPossibleSpots(Coordinate coordinate, int xChange, int yChange) {
    List<Coordinate> myCoords;
    List<Integer> myXInts = Arrays.asList(xChange);
    List<Integer> myYInts = Arrays.asList(yChange);

    myCoords = diagonalSquares(coordinate,myXInts,myYInts);

    return myCoords;
  }

}
