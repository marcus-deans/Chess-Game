package ooga.logic.board.Pieces.Movements;

import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.Coordinate;
import ooga.logic.board.CoordinateUseCase;

abstract public class Movement{
  protected CoordinateUseCase Diagonal(CoordinateUseCase myCoordinate, int xAmount, int yAmount){
    myCoordinate.setX_pos(myCoordinate.getX_pos() + xAmount);
    myCoordinate.setY_pos(myCoordinate.getY_pos() + yAmount);
    return myCoordinate;
  }

  protected CoordinateUseCase Forward(CoordinateUseCase myCoordinate, int yAmount) {
    return Diagonal(myCoordinate,0,yAmount);
  }

  protected CoordinateUseCase Sideways(CoordinateUseCase myCoordinate, int xAmount) {
    return Diagonal(myCoordinate,xAmount,0);
  }

  public abstract List<Coordinate> getPossibleMoves(CoordinateUseCase coordinate);

  protected List<Coordinate> availableSquares(CoordinateUseCase myCoordinate,int[] addXAmount, int[] addYAmount){
    List<Coordinate> myCoordinateList = new ArrayList<>();
    CoordinateUseCase moveCoordinate;

    for (int xAmt : addXAmount){
      for (int yAmt: addYAmount){
        if (!(xAmt == 0 && yAmt == 0)) {
          moveCoordinate = Diagonal(myCoordinate,xAmt,yAmt);
          if (isValidSquare(moveCoordinate)) {
            myCoordinateList.add(moveCoordinate);
          }
        }
      }
    }
    return myCoordinateList;

  }

  private boolean isValidSquare(CoordinateUseCase captureCoordinate) {
    // TODO: IMPLEMENT EDGE POLICIES
    return !(captureCoordinate.getX_pos() < 0 || captureCoordinate.getY_pos() < 0
        || captureCoordinate.getX_pos() > 7 || captureCoordinate.getY_pos() > 7);
  }

}
