package ooga.logic.board.Pieces;

import java.util.List;
import ooga.logic.board.Pieces.Interfaces.CaptureLogic;
import ooga.logic.board.Pieces.Interfaces.MoveLogic;
import ooga.logic.board.Pieces.Interfaces.PieceLogic;
import ooga.logic.board.Pieces.Interfaces.PromoteLogic;
import ooga.logic.board.Pieces.PieceCollection.PieceCollection;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;
import ooga.logic.board.coordinate.Coordinate;

abstract public class Piece implements PieceLogic, MoveLogic, CaptureLogic, PromoteLogic {
  private Coordinate myCoordinate;
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
