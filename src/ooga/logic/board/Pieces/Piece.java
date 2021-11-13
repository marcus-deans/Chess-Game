package ooga.logic.board.Pieces;

import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.Coordinate;
import ooga.logic.board.CoordinateUseCase;
import ooga.logic.board.Pieces.Interfaces.CaptureLogic;
import ooga.logic.board.Pieces.Interfaces.MoveLogic;
import ooga.logic.board.Pieces.Interfaces.PieceLogic;
import ooga.logic.board.Pieces.Interfaces.PromoteLogic;

abstract public class Piece implements PieceLogic, MoveLogic, CaptureLogic, PromoteLogic {
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

  @Override
  public boolean canCapture(Coordinate captureCoordinate) {
    return getPossibleCaptures().contains(captureCoordinate);
  }


  @Override
  public void updatePosition(Coordinate passedCoordinate) {
    setMyCoordinate(passedCoordinate);
  }


  @Override
  public void setCoordinate(Coordinate passedCoordinate) {
    setMyCoordinate(passedCoordinate);
  }

  
  @Override
  public List<Coordinate> promotionSquares() {
    return null;
  }

  @Override
  public List<PieceLogic> possiblePromotionPieces() {
    return null;
  }


}
