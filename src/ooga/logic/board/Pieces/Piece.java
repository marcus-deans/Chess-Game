package ooga.logic.board.Pieces;

import ooga.logic.board.Coordinate;

abstract public class Piece implements PieceLogic{
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



}
