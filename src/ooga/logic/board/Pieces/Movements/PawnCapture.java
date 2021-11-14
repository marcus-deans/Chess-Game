package ooga.logic.board.Pieces.Movements;

import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.Coordinate;
import ooga.logic.board.CoordinateUseCase;

public class PawnCapture extends Movement{

  public List<Coordinate> getPossibleMoves(CoordinateUseCase myCoordinate) {
    int[] addXAmount = new int[]{-1,1};
    int[] addYAmount = new int[]{1};
    return availableSquares(myCoordinate, addXAmount,addYAmount);
  }
}
