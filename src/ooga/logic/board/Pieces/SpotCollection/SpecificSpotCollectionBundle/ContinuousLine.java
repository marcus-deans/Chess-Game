package ooga.logic.board.Pieces.SpotCollection.SpecificSpotCollectionBundle;

import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

public class ContinuousLine extends SpecificSpotCollection {

  public List<Coordinate> getPossibleSpots(Coordinate coordinate, int xDirection,int yDirection) {
    for (int i = 1; i < 8; i++){
      addToMyXInts(xDirection * i);
      addToMyYInts(yDirection * i);
    }

    return diagonalSquares(coordinate, getMyXInts(), getMyYInts());
  }

}