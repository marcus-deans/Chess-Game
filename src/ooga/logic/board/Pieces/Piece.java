package ooga.logic.board.Pieces;

import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.Coordinate;
import ooga.logic.board.Pieces.Interfaces.CaptureLogic;
import ooga.logic.board.Pieces.Interfaces.MoveLogic;
import ooga.logic.board.Pieces.Interfaces.PieceLogic;
import ooga.logic.board.Pieces.Interfaces.PromoteLogic;
import ooga.logic.board.Pieces.PieceCollection.DefaultPromotionPieces;
import ooga.logic.board.Pieces.PieceCollection.PieceCollection;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;

abstract public class Piece implements PieceLogic, MoveLogic, CaptureLogic, PromoteLogic {
  private Coordinate myCoordinate;
  private int myRank;
  private int myFile;
  private SpotCollection myMovement;
  private SpotCollection myCapture;
  private SpotCollection myPromotionSpots;
  private PieceCollection myPromotionOptions;



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
    Coordinate moveCoordinate;

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

  private boolean isValidSquare(Coordinate captureCoordinate) {
    // TODO: IMPLEMENT EDGE POLICIES
    return !(captureCoordinate.getX_pos() < 0 || captureCoordinate.getY_pos() < 0
    || captureCoordinate.getX_pos() > 7 || captureCoordinate.getY_pos() > 7);

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
  public List<Piece> possiblePromotionPieces(){
    return myPromotionOptions.getPossiblePieces();
  }

  protected Coordinate Diagonal(Coordinate myCoordinate, int xAmount, int yAmount){
    myCoordinate.setX_pos(myCoordinate.getX_pos() + xAmount);
    myCoordinate.setY_pos(myCoordinate.getY_pos() + yAmount);
    return myCoordinate;
  }

  protected Coordinate Forward(int yAmount) {
    return Diagonal(getCoordinate(),0,yAmount);
  }

  protected Coordinate Sideways(int xAmount) {
    return Diagonal(getCoordinate(),xAmount,0);
  }


  @Override
  public void setState() {

  }


  @Override
  public List<Coordinate> getPossibleCaptures() {
    return myCapture.getPossibleSpots(getCoordinate());
  }

  @Override
  public List<Coordinate> getPossibleMoves() {
    return myMovement.getPossibleSpots(getCoordinate());
  }

  protected void setMyMovement(SpotCollection movementToSet){
    myMovement = movementToSet;
  }
  protected void setMyCapture(SpotCollection captureToSet){
    myCapture = captureToSet;
  }
  protected void setMyPromotionSpots(SpotCollection promotionToSet){
    myPromotionSpots = promotionToSet;
  }

  protected void setMyPromotionPieces(PieceCollection myPieceCollection) {
    myPromotionOptions = myPieceCollection;
  }

  @Override
  public List<Coordinate> promotionSquares() {
    return myPromotionSpots.getPossibleSpots(myCoordinate);
  }


}
