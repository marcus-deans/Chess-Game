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
  private CoordinateUseCase myCoordinate;
  private int myRank;
  private int myFile;


  @Override
  public CoordinateUseCase getCoordinate(){
    return myCoordinate;
  }

  protected int getMyXCoordinate(){
    return getCoordinate().getX_pos();
  }

  protected int getMyYCoordinate(){
    return getCoordinate().getY_pos();
  }

  protected void setMyCoordinate(CoordinateUseCase newCoordinate){
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
    CoordinateUseCase moveCoordinate;

    for (int xAmt : addXAmount){
      for (int yAmt: addYAmount){
        if (!(xAmt == 0 && yAmt == 0)) {
          moveCoordinate = Diagonal(getCoordinate(),xAmt,yAmt);
          if (isValidSquare(moveCoordinate)) {
            myCoordinateList.add(moveCoordinate);
          }
        }
      }
    }
    return myCoordinateList;

  }

  private boolean isValidSquare(CoordinateUseCase captureCoordinate) {
    // TODO: IMPLEMENT EDGE POLICIES
    return !(captureCoordinate.getX_pos() < 0 || captureCoordinate.getY_pos() < 0
    || captureCoordinate.getX_pos() > 7 || captureCoordinate.getY_pos() > 7);

  }

  @Override
  public boolean canCapture(CoordinateUseCase captureCoordinate) {
    return getPossibleCaptures().contains(captureCoordinate);
  }


  @Override
  public void updatePosition(CoordinateUseCase passedCoordinate) {
    setMyCoordinate(passedCoordinate);
  }


  @Override
  public void setCoordinate(CoordinateUseCase passedCoordinate) {
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

  protected CoordinateUseCase Diagonal(CoordinateUseCase myCoordinate, int xAmount, int yAmount){
    myCoordinate.setX_pos(myCoordinate.getX_pos() + xAmount);
    myCoordinate.setY_pos(myCoordinate.getY_pos() + yAmount);
    return myCoordinate;
  }

  protected CoordinateUseCase Forward(int yAmount) {
    return Diagonal(getCoordinate(),0,yAmount);
  }

  protected CoordinateUseCase Sideways(int xAmount) {
    return Diagonal(getCoordinate(),xAmount,0);
  }


  @Override
  public void setState() {

  }


}
