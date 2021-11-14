package ooga.logic.board.Pieces.Movements;

import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.Coordinate;
import ooga.logic.board.CoordinateUseCase;

public class PawnMovement extends Movement{

  public List<Coordinate> getPossibleMoves(CoordinateUseCase myCoordinate) {
    List<Coordinate> myPossibleMoves = new ArrayList<>();
    myPossibleMoves.add(Forward(myCoordinate, 1));
    if (myCoordinate.getY_pos() == 1){
      myPossibleMoves.add(Forward(myCoordinate,2));
    }
    return myPossibleMoves;
  }
}
