package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import ooga.logic.board.Pieces.SpotCollection.SpecificSpotCollectionBundle.ContinuousLine;
import ooga.logic.board.Pieces.SpotCollection.SpecificSpotCollectionBundle.OneTimeDirection;
import ooga.logic.board.coordinate.Coordinate;

/**
 * Class that stores a collection of spots
 */
abstract public class SpotCollection implements SpotCollectionInterface{
  private ResourceBundle pieceProperties;
  private static final String BASE = SpotCollection.class.getPackageName() + ".resources";
  private static final String DIRECTION = "Directions";
  private int boardWidth;

  /**
   * Set width and piece Properties
   * Pass width back to define bounds
   * @param width max of width of height of board
   */
  public SpotCollection(int width){
    boardWidth = width;
    pieceProperties = ResourceBundle.getBundle(String.format("%s.%s",BASE,DIRECTION));
  }

  /**
   * return a list of list of coordinates, where within each list a coordinate being reached
   * depends on the coordinate before it
   * @param coordinate the coordinate we are currently at
   * @return the coordinates we could reach with no regard to bounds
   */
  @Override
  public abstract List<List<Coordinate>> getPossibleSpots(Coordinate coordinate);

  protected ResourceBundle getPieceProperties(){
    return pieceProperties;
  }


  protected List<List<Coordinate>> availableSquares(Coordinate myCoordinate,int[] addXAmount, int[] addYAmount){
    List<List<Coordinate>> myCoordinateList = new ArrayList<>();
    List<Coordinate> moveCoordinate;

    for (int xAmt : addXAmount){
      for (int yAmt: addYAmount){
        if (!originalCoordinate(xAmt, yAmt)) {
          moveCoordinate = (new OneTimeDirection(getBoardWidth())).getPossibleSpots(myCoordinate,xAmt,yAmt);
          if (moveCoordinate.size() != 0){
            myCoordinateList.add(moveCoordinate);
          }
        }
      }
    }
    return myCoordinateList;

  }
  private boolean originalCoordinate(int xAmt, int yAmt) {
    return xAmt == 0 && yAmt == 0;
  }

  protected int[] stringToIntArr(String myString){
    List<Integer> myArrayList = new ArrayList<>();
    String[] arr=myString.replaceAll("\\[|\\]| ", "").split(",");
    for(int i=0;i<arr.length;i++){

      myArrayList.add(Integer.parseInt(arr[i]));
    }

    int[] myArr = myArrayList.stream().mapToInt(i -> i).toArray();

    return myArr;
  }

  protected List<List<Coordinate>> ContinuousPossibleSpots(String piece_as_string, Coordinate myCoordinate) {
    List<List<Coordinate>> myBigListOfCoordLists = new ArrayList<>();
    String[] myDirections = pieceProperties.getString(piece_as_string).split(",");
    for (String x : myDirections){
      List<Coordinate> myCoords = new ArrayList<>();
      int[] myDirection = stringToIntArr(pieceProperties.getString(x));
      myCoords.addAll(new ContinuousLine(getBoardWidth()).getPossibleSpots(myCoordinate, myDirection[0],myDirection[1]));
      myBigListOfCoordLists.add(myCoords);
    }

    return myBigListOfCoordLists;
  }

  protected List<List<Coordinate>> OneTimePossibleSpots(String piece_as_string, Coordinate myCoordinate) {
    List<Coordinate> myCoords = new ArrayList<>();
    String[] myDirections = pieceProperties.getString(piece_as_string).split(",");
    for (String x : myDirections){
      int[] myDirection = stringToIntArr(pieceProperties.getString(x));
      myCoords.addAll(new OneTimeDirection(getBoardWidth()).getPossibleSpots(myCoordinate, myDirection[0],myDirection[1]));
    }
    List<List<Coordinate>> myBiggerCoordList = new ArrayList<>();
    myBiggerCoordList.add(myCoords);

    return myBiggerCoordList;
  }

  protected List<List<Coordinate>> doubleSymmetricOver(int[] signs, Coordinate myCoordinate, String[] myDirections) {
    List<List<Coordinate>> myBigList = new ArrayList<>();
    List<Coordinate> myCoords;
    for (int xSign : signs){
      for (int ySign : signs){
        for (String direction: myDirections) {
          int[] myDirection = stringToIntArr(pieceProperties.getString(direction));
          myCoords = new OneTimeDirection(getBoardWidth()).getPossibleSpots(myCoordinate, xSign * myDirection[0],
              ySign * myDirection[1]);
          myBigList.add(myCoords);

          myCoords = new OneTimeDirection(getBoardWidth()).getPossibleSpots(myCoordinate, xSign * myDirection[1],
              ySign * myDirection[0]);
          myBigList.add(myCoords);

        }
      }
    }
    return myBigList;
  }

  protected int getBoardWidth(){
    return boardWidth;
  }


}
