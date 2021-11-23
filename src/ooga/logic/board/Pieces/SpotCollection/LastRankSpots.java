package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;

public class LastRankSpots extends SpotCollection {
  private final List<Integer> DEFAULT_RANK = Arrays.asList(7);
  private final List<Integer> DEFAULT_FILE = Arrays.asList(0,1,2,3,4,5,6,7);


  @Override
  public List<Coordinate> getPossibleSpots(Coordinate coordinate) {
    List<Coordinate> myCoordinateList = new ArrayList<>();
    Coordinate newCapture;

    for (int xPos : DEFAULT_FILE){
      for (int yPos: DEFAULT_RANK){
        newCapture = new GameCoordinate(xPos,yPos);
        myCoordinateList.add(newCapture);
      }
    }
    return myCoordinateList;
  }
}
