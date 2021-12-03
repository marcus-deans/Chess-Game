package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import ooga.logic.board.Pieces.SpotCollection.SpecificSpotCollectionBundle.ContinuousLine;
import ooga.logic.board.Pieces.SpotCollection.SpecificSpotCollectionBundle.OneTimeDirection;
import ooga.logic.board.board.GameBoard;
import ooga.logic.board.coordinate.Coordinate;

abstract public class SpotCollection implements SpotCollectionInterface{
  private ResourceBundle pieceProperties;
  private ResourceBundle teamProperties;
  private static final String BASE = SpotCollection.class.getPackageName() + ".resources";
  private static final String DIRECTION = "Directions";
  private static final String TEAM_DEFAULT_DIRECTIONS = "TeamDefaultDirections";


  public SpotCollection(){
    pieceProperties = ResourceBundle.getBundle(String.format("%s.%s",BASE,DIRECTION));
    teamProperties = ResourceBundle.getBundle(String.format("%s.%s",BASE, TEAM_DEFAULT_DIRECTIONS));
  }

  @Override
  public abstract List<Coordinate> getPossibleSpots(Coordinate coordinate);

  protected ResourceBundle getPieceProperties(){
    return pieceProperties;
  }

  protected ResourceBundle setPieceProperties(){
    return teamProperties;
  }

  protected List<Coordinate> availableSquares(Coordinate myCoordinate,int[] addXAmount, int[] addYAmount){
    List<Coordinate> myCoordinateList = new ArrayList<>();
    List<Coordinate> moveCoordinate;

    for (int xAmt : addXAmount){
      for (int yAmt: addYAmount){
        if (!originalCoordinate(xAmt, yAmt)) {
          moveCoordinate = (new OneTimeDirection()).getPossibleSpots(myCoordinate,xAmt,yAmt);
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

  protected int[] stringToIntArr(String myString){
    List<Integer> myArrayList = new ArrayList<>();
    String[] arr=myString.replaceAll("\\[|\\]| ", "").split(",");
    for(int i=0;i<arr.length;i++){

      myArrayList.add(Integer.parseInt(arr[i]));
    }

    int[] myArr = myArrayList.stream().mapToInt(i -> i).toArray();

    return myArr;
  }

  protected List<Coordinate> ContinuousPossibleSpots(String piece_as_string, Coordinate myCoordinate) {
    List<Coordinate> myCoords = new ArrayList<>();
    String[] myDirections = pieceProperties.getString(piece_as_string).split(",");
    for (String x : myDirections){
      int[] myDirection = stringToIntArr(pieceProperties.getString(x));
      myCoords.addAll(new ContinuousLine().getPossibleSpots(myCoordinate, myDirection[0],myDirection[1]));
    }

    return myCoords;
  }

  protected List<Coordinate> OneTimePossibleSpots(String piece_as_string, Coordinate myCoordinate) {
    List<Coordinate> myCoords = new ArrayList<>();
    String[] myDirections = pieceProperties.getString(piece_as_string).split(",");
    for (String x : myDirections){
      int[] myDirection = stringToIntArr(pieceProperties.getString(x));
      myCoords.addAll(new OneTimeDirection().getPossibleSpots(myCoordinate, myDirection[0],myDirection[1]));
    }

    return myCoords;
  }

  protected List<Coordinate> doubleSymmetricOver(int[] signs, Coordinate myCoordinate, String[] myDirections) {
    List<Coordinate> myCoords = new ArrayList<>();
    for (int xSign : signs){
      for (int ySign : signs){
        for (String direction: myDirections) {
          int[] myDirection = stringToIntArr(pieceProperties.getString(direction));
          myCoords.addAll(new OneTimeDirection().getPossibleSpots(myCoordinate, xSign * myDirection[0],
              ySign * myDirection[1]));
          myCoords.addAll(new OneTimeDirection().getPossibleSpots(myCoordinate, xSign * myDirection[1],
              ySign * myDirection[0]));
        }
      }
    }
    return myCoords;
  }

}
