package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;

public class PromotionSpots extends SpotCollection {
  private List<Integer> rank;
  private List<Integer> file;
  private final List<Integer> DEFAULT_RANK = Arrays.asList(7);
  private final List<Integer> DEFAULT_FILE = Arrays.asList(0,1,2,3,4,5,6,7);

  public PromotionSpots(){
    rank = DEFAULT_RANK;
    file = DEFAULT_FILE;
  }

  public void setRank(List<Integer> rankList){
    this.rank = rankList;
  }
  public void setFile(List<Integer> fileList){
    this.file = fileList;
  }



  @Override
  public List<Coordinate> getPossibleSpots(Coordinate myCoordinate) {
    List<Coordinate> myCoordinateList = new ArrayList<>();
    Coordinate newCapture;

    List<Integer> xOfSquares = new ArrayList<>();
    for (int x : file){
      xOfSquares.add(x);
    }
    List<Integer> yOfSquares = new ArrayList<>();
    for (int y : rank){
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

}
