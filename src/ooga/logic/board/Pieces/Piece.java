package ooga.logic.board.Pieces;

import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.Coordinate;
import ooga.logic.board.CoordinateUseCase;
import ooga.logic.board.Pieces.Interfaces.PieceLogic;

abstract public class Piece implements PieceLogic {
  private Coordinate myCoordinate;
  private int myRank;
  private int myFile;


  @Override
  public Coordinate getCoordinate(){
    return myCoordinate;
  }

  protected int getMyXCoordinate(){
    return getCoordinate().getX_pos();
  }

  protected int getMyYCoordinate(){
    return getCoordinate().getY_pos();
  }

  protected void setMyCoordinate(Coordinate newCoordinate){
    myCoordinate = newCoordinate;
  }

  public int getMyRank() {
    return myRank;
  }

  public void setMyRank(int myRank) {
    this.myRank = myRank;
  }

  public int getMyFile() {
    return myFile;
  }

  public void setMyFile(int myFile) {
    this.myFile = myFile;
  }

  protected void updateRankAndFile() {
    setMyRank(getMyXCoordinate());
    setMyFile(getMyYCoordinate());
  }

  protected List<Coordinate> availableSquares(int[] addXAmount, int[] addYAmount){
    List<Coordinate> myCoordinateList = new ArrayList<>();
    CoordinateUseCase moveCoordinate = new CoordinateUseCase();

    for (int xAmt : addXAmount){
      for (int yAmt: addYAmount){
        if (!(xAmt == 0 && yAmt == 0)) {
          moveCoordinate.setCoordinate(getMyXCoordinate() + xAmt, getMyYCoordinate() + yAmt);
          if (isValidSquare(moveCoordinate)) {
            myCoordinateList.add(moveCoordinate);
          }
        }
      }
    }
    return myCoordinateList;

  }

  private boolean isValidSquare(Coordinate captureCoordinate) {
    // TODO: IMPLEMENT EDGE POLICIES
    return true;
  }


}
