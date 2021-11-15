package ooga.logic.board.Pieces.SpotCollection.SpecificSpotCollectionBundle;

import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

abstract public class SpecificSpotCollection {

  private List<Integer> myXInts;
  private List<Integer> myYInts;

  protected SpecificSpotCollection(){
    myXInts = new ArrayList<>();
    myYInts = new ArrayList<>();
  }

  public abstract List<Coordinate> getPossibleSpots(Coordinate coordinate, int xChange, int yChange);


  protected void addToMyXInts(int toAdd){
    myXInts.add(toAdd);
  }

  protected void addToMyYInts(int toAdd){
    myYInts.add(toAdd);
  }

  protected List<Integer> getMyXInts(){
    return myXInts;
  }
  protected List<Integer> getMyYInts(){
    return myYInts;
  }

  protected List<Coordinate> diagonalSquares
      (Coordinate coordinate, List<Integer> myXInts, List<Integer> myYInts) {
    List<Coordinate> myCoordinateList = new ArrayList<>();
    Coordinate moveCoordinate;
    int i = 0;
    while (i < myXInts.size()){
      moveCoordinate = Diagonal(coordinate,myXInts.get(i),myYInts.get(i));
      if (moveCoordinate != null){
        myCoordinateList.add(moveCoordinate);
      }
      i++;
    }

    return myCoordinateList;
  }

  protected Coordinate Diagonal(Coordinate myCoordinate, int xAmount, int yAmount){
    myCoordinate.setX_pos(myCoordinate.getX_pos() + xAmount);
    myCoordinate.setY_pos(myCoordinate.getY_pos() + yAmount);
    if (isValidSquare(myCoordinate)){
      return myCoordinate;
    }
    return null;
  }

  private boolean isValidSquare(Coordinate captureCoordinate) {
    // TODO: IMPLEMENT EDGE POLICIES
    return !(captureCoordinate.getX_pos() < 0 || captureCoordinate.getY_pos() < 0
        || captureCoordinate.getX_pos() > 7 || captureCoordinate.getY_pos() > 7);
  }

}
