package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.Pieces.SpotCollection.SpecificSpotCollectionBundle.OneTimeDirection;
import ooga.logic.board.coordinate.Coordinate;

abstract public class SpotCollection {

  public abstract List<Coordinate> getPossibleSpots(Coordinate coordinate);

  protected List<Coordinate> availableSquares(Coordinate myCoordinate,List<Integer> addXAmount,
      List<Integer>addYAmount){
    List<Coordinate> myCoordinateList = new ArrayList<>();
    List<Coordinate> moveCoordinate;

    for (int xAmt : addXAmount){
      for (int yAmt: addYAmount){
        Coordinate myOriginalCopy = myCoordinate;
        if (!originalCoordinate(xAmt, yAmt)) {
//          int newX = myCoordinate.getX_pos() + xAmt;
//          int newY = myCoordinate.getY_pos() + yAmt;
//          System.out.println(newX + " " + newY);
          moveCoordinate = (new OneTimeDirection()).getPossibleSpots(myOriginalCopy,xAmt,yAmt);
         if (moveCoordinate.size() != 0){
           myCoordinateList.addAll(moveCoordinate);
         }
        }
      }
    }
    return myCoordinateList;

  }

  private boolean originalCoordinate(int xAmt, int yAmt) {
    return xAmt == 0 && yAmt == 0;
  }

}
