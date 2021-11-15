package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.Pieces.SpotCollection.SpecificSpotCollectionBundle.OneTimeDiagonal;
import ooga.logic.board.coordinate.Coordinate;

abstract public class SpotCollection {

  public abstract List<Coordinate> getPossibleSpots(Coordinate coordinate);

  protected List<Coordinate> availableSquares(Coordinate myCoordinate,List<Integer> addXAmount,
      List<Integer>addYAmount){
    List<Coordinate> myCoordinateList = new ArrayList<>();
    List<Coordinate> moveCoordinate;

    for (int xAmt : addXAmount){
      for (int yAmt: addYAmount){
        if (!originalCoordinate(xAmt, yAmt)) {
          moveCoordinate = new OneTimeDiagonal().getPossibleSpots(myCoordinate,xAmt,yAmt);
          myCoordinateList.addAll(moveCoordinate);
        }
      }
    }
    return myCoordinateList;

  }

  private boolean originalCoordinate(int xAmt, int yAmt) {
    return xAmt == 0 && yAmt == 0;
  }

}
