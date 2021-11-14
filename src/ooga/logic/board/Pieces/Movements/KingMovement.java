package ooga.logic.board.Pieces.Movements;

import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.Coordinate;

public class KingMovement extends Movement{

  public List<Coordinate> getPossibleMoves(Coordinate myCoordinate) {
    int[] myXInts = new int[]{-1,0,1};
    int[] myYInts = new int[]{-1,0,1};
    return availableSquares(myCoordinate, myXInts, myYInts);
  }
}