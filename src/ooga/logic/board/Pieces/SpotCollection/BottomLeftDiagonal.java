package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

public class BottomLeftDiagonal extends SpotCollection{

  @Override
  public List<Coordinate> getPossibleSpots(Coordinate coordinate) {
    for (int i = 1; i < 8; i++){
      addToMyXInts(-i);
      addToMyYInts(-i);
    }
    return diagonalSquares(coordinate, getMyXInts(), getMyYInts());
  }

}