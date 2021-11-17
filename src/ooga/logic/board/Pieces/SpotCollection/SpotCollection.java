package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

abstract public class SpotCollection {
  protected Coordinate Diagonal(Coordinate myCoordinate, int xAmount, int yAmount){
    myCoordinate.setX_pos(myCoordinate.getX_pos() + xAmount);
    myCoordinate.setY_pos(myCoordinate.getY_pos() + yAmount);
    return myCoordinate;
  }

  protected Coordinate Forward(Coordinate myCoordinate, int yAmount) {
    return Diagonal(myCoordinate,0,yAmount);
  }

  protected Coordinate Sideways(Coordinate myCoordinate, int xAmount) {
    return Diagonal(myCoordinate,xAmount,0);
  }

  public abstract List<Coordinate> getPossibleSpots(Coordinate coordinate);

  protected List<Coordinate> availableSquares(Coordinate myCoordinate,int[] addXAmount, int[] addYAmount){
    List<Coordinate> myCoordinateList = new ArrayList<>();
    Coordinate moveCoordinate;

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

  private boolean isValidSquare(Coordinate captureCoordinate) {
    // TODO: IMPLEMENT EDGE POLICIES
    return !(captureCoordinate.getX_pos() < 0 || captureCoordinate.getY_pos() < 0
        || captureCoordinate.getX_pos() > 7 || captureCoordinate.getY_pos() > 7);
  }

}
