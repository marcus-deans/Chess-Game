package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;

public class PromotionSpots extends SpotCollection implements AcceptsCoordinates {
  private final List<Integer> DEFAULT_RANK = Arrays.asList(7);
  private final List<Integer> DEFAULT_FILE = Arrays.asList(0,1,2,3,4,5,6,7);

  private List<Coordinate> myCoordinates;

  public PromotionSpots(){
    myCoordinates = getDefaultPossibleSpots();
  }
  
  private List<Coordinate> getDefaultPossibleSpots() {
    List<Coordinate> myCoordinateList = new ArrayList<>();
    Coordinate newCapture;

    List<Integer> xOfSquares = new ArrayList<>();
    for (int x : DEFAULT_FILE){
      xOfSquares.add(x);
    }
    List<Integer> yOfSquares = new ArrayList<>();
    for (int y : DEFAULT_RANK){
      yOfSquares.add(y);
    }

    for (int xPos : xOfSquares){
      for (int yPos: yOfSquares){
        newCapture = new GameCoordinate(xPos,yPos);
        myCoordinateList.add(newCapture);
      }
    }
    return myCoordinateList;
  }


  @Override
  public List<Coordinate> getPossibleSpots(Coordinate coordinate) {
    return myCoordinates;
  }

  @Override
  public void addCoordinates(List<Coordinate> newCoords) {
    myCoordinates.addAll(newCoords);
  }

  @Override
  public void removeCoordinates(List<Coordinate> removeCoords) {
    myCoordinates.removeAll(removeCoords);
  }

  @Override
  public void removeAllCoordinates() {
    myCoordinates = new ArrayList<>();
  }
}
