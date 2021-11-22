package ooga.logic.board.Pieces.PieceBundle;

import java.util.List;
import java.util.ResourceBundle;
import ooga.logic.board.Pieces.Interfaces.CaptureLogic;
import ooga.logic.board.Pieces.Interfaces.MoveLogic;
import ooga.logic.board.Pieces.Interfaces.PieceLogic;
import ooga.logic.board.Pieces.Interfaces.PromoteLogic;
import ooga.logic.board.Pieces.PieceCollection.PieceCollection;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;
import ooga.logic.board.coordinate.Coordinate;

abstract public class Piece implements PieceLogic, MoveLogic, CaptureLogic, PromoteLogic{
  private Coordinate myCoordinate;
  private SpotCollection myMovement;
  private SpotCollection myCapture;
  private SpotCollection myPromotionSpots;
  private PieceCollection myPromotionOptions;
  private int team;
  private int pieceValue;
  private boolean canJump;


  private ResourceBundle PieceProperties;
  private ResourceBundle DefaultProperties;

  private static final String PIECES_PACKAGE = Piece.class.getPackageName() + ".resources.";
  private static final String DEFAULT_TO_STRING = "Default";

  public Piece(String pieceToString) {
    setPieceProperties(pieceToString);
    setDefaultProperties();
  }


  protected ResourceBundle getPieceProperties() {
    return PieceProperties;
  }

  protected void setPieceProperties(String PIECE_AS_STRING) {
    PieceProperties=ResourceBundle.getBundle(PIECES_PACKAGE+PIECE_AS_STRING);
  }

  protected ResourceBundle getDefaultProperties() {
    return DefaultProperties;
  }

  protected void setDefaultProperties() {
    DefaultProperties=ResourceBundle.getBundle(PIECES_PACKAGE+DEFAULT_TO_STRING);
  }


  @Override
  public Coordinate getCoordinate(){
    return myCoordinate;
  }


  @Override
  public boolean canCapture(Coordinate captureCoordinate) {
    return getPossibleCaptures().contains(captureCoordinate);
  }

  @Override
  public void setCoordinate(Coordinate passedCoordinate) {
    myCoordinate = passedCoordinate;
  }


  @Override
  public List<Piece> possiblePromotionPieces(){
    return myPromotionOptions.getPossiblePieces();
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

  @Override
  public void setTeam(int newTeam) {
    this.team = newTeam;
  }

  @Override
  public int getTeam() {
    return team;
  }

  @Override
  public void setValue(int value) {
    pieceValue = value;
  }

  @Override
  public int getValue() {
    return pieceValue;
  }

  @Override
  public void setCanJump(boolean newJump) {
    canJump = newJump;
  }

  @Override
  public boolean getCanJump() {
    return this.canJump;
  }
}
