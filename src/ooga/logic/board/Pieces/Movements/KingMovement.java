package ooga.logic.board.Pieces.Movements;

import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.Coordinate;
import ooga.logic.board.CoordinateUseCase;

public class KingMovement extends Movement{

  public List<Coordinate> getPossibleMoves(CoordinateUseCase myCoordinate) {
    int[] myXInts = new int[]{-1,0,1};
    int[] myYInts = new int[]{-1,0,1};
    return availableSquares(myCoordinate, myXInts, myYInts);
  }
}