package ooga.logic.board.Pieces.SpotCollection.SpecificSpotCollectionBundle;

import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;

/**
 * This class takes care of the logic behind finding specific spots or finding collections of spots
 * @author Amr Tagel-Din
 */
abstract public class SpecificSpotCollection implements SpecificSpotCollectionInterface{
  private int myWidth;
  /**
   * From the interface, get possible spots either once or continuously down a given x and y
   * @param coordinate The coordinate that we are starting at
   * @param xChange the xChange is the change in the xDirection we will take either once, or repeatedly
   * @param yChange the yChange is the change in the yDirection we will take either once, or repeatedly
   * @return
   */
  @Override
  public abstract List<Coordinate> getPossibleSpots(Coordinate coordinate, int xChange, int yChange);

  protected List<Coordinate> diagonalSquares(Coordinate coordinate, Integer myXInt, Integer myYInt) {
    List<Coordinate> myCoordinateList = new ArrayList<>();
    List<Coordinate> individualCoords;
    int i = 1;
    while (i <= myWidth){
      individualCoords = Diagonal(coordinate,myXInt * i,myYInt * i);
      if (DiagonalIsInvalid(individualCoords)){
        break;
      }
      myCoordinateList.addAll(individualCoords);
      i++;
    }

    return myCoordinateList;
  }

  private boolean DiagonalIsInvalid(List<Coordinate> individualCoords) {
    return individualCoords.size() == 0;
  }

  protected List<Coordinate> Diagonal(Coordinate myCoordinate, int xAmount, int yAmount){
    List<Coordinate> myCoords = new ArrayList<>();
    Coordinate myNewCoordinate = new GameCoordinate(myCoordinate.getX_pos() + xAmount,myCoordinate.getY_pos() + yAmount);
    myCoords.add(myNewCoordinate);
    return myCoords;
  }

  protected void setMyWidth(int width) {
    myWidth = width;
  }

}
