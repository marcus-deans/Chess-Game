package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.Coordinate;
import ooga.logic.board.GameCoordinate;

public class finalRankPromotionSpots extends SpotCollection {

  public List<Coordinate> getPossibleSpots(Coordinate myCoordinate) {
    List<Coordinate> myCoordinateList = new ArrayList<>();
    Coordinate newCapture;

    List<Integer> xOfSquares = new ArrayList<>();
    for (int i = 0; i < 8; i++){
      xOfSquares.add(i);
    }
    List<Integer> yOfSquares = new ArrayList<>();
    yOfSquares.add(7);


    for (int xPos : xOfSquares){
      for (int yPos: yOfSquares){
        newCapture = new GameCoordinate(xPos,yPos);
        myCoordinateList.add(newCapture);
      }
    }
    return myCoordinateList;
  }

}
