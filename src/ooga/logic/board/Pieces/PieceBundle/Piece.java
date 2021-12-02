package ooga.logic.board.Pieces.PieceBundle;

import java.util.ResourceBundle;
import ooga.logic.board.Pieces.Interfaces.CaptureLogic;
import ooga.logic.board.Pieces.Interfaces.MoveLogic;
import ooga.logic.board.Pieces.Interfaces.PieceLogic;
import ooga.logic.board.Pieces.Interfaces.PromoteLogic;
import ooga.logic.board.Pieces.PieceCollection.DefaultPromotionPieces;
import ooga.logic.board.Pieces.PieceCollection.PieceCollection;
import ooga.logic.board.Pieces.SpotCollection.LastRankSpots;
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
  private boolean isCheckable;

  private ResourceBundle PieceProperties;
  private ResourceBundle DefaultProperties;

  private static final String PIECES_PACKAGE = Piece.class.getPackageName() + ".resources.";
  private static final String DEFAULT_TO_STRING = "Default";

  private boolean teamMatters;

  public Piece(String pieceToString, int team, Coordinate myCoordinate) {
    setPieceProperties(pieceToString);
    setDefaultProperties();
    setMyTeamMatters();
    setTeam(team);
    setCoordinate(myCoordinate);
    setJump();
    setMyTeamMatters();
    setMovement(pieceToString);
    setCapture(pieceToString);
    setPromotionSpots();
    setMyCheckable();
  }

  private void setMyTeamMatters() {
    try{
      setTeamMatters(Boolean.parseBoolean(getPieceProperties().getString("teamMatters")));
    }
    catch (Exception e){
      setTeamMatters(Boolean.parseBoolean(getDefaultProperties().getString("teamMatters")));
    }
  }

  private void setTeamMatters(boolean value){
    teamMatters = value;
  }

  protected void setPromotionSpots(){
    if (PieceProperties.containsKey("promotion")){
      setMyPromotionSpots(new LastRankSpots(new DefaultPromotionPieces()));
    }

  }

  private void setJump() {
    try{
      setCanJump(Boolean.parseBoolean(getPieceProperties().getString("jump")));
    }
    catch (Exception e){
      setCanJump(Boolean.parseBoolean(getDefaultProperties().getString("jump")));
    }
  }

  private void setMyCheckable() {
    try{
      setCanJump(Boolean.parseBoolean(getPieceProperties().getString("isCheckable")));
    }
    catch (Exception e){
      setCanJump(Boolean.parseBoolean(getDefaultProperties().getString("isCheckable")));
    }
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
    return getPossibleCaptures().getPossibleSpots(myCoordinate).contains(captureCoordinate);
  }

  @Override
  public void setCoordinate(Coordinate passedCoordinate) {
    myCoordinate = passedCoordinate;
  }


  @Override
  public PieceCollection possiblePromotionPieces(){
    return myPromotionOptions;//.getPossiblePieces();
  }

  @Override
  public SpotCollection getPossibleCaptures() {
    return myCapture;//.getPossibleSpots(getCoordinate());
  }

  @Override
  public SpotCollection getPossibleMoves() {
    return myMovement;//.getPossibleSpots(getCoordinate());
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
  public SpotCollection promotionSquares() {
    return myPromotionSpots;//.getPossibleSpots(myCoordinate);
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

  private void setMovement(String pieceToString) {
    System.out.println();
    try{
      setMyMovement(
          (SpotCollection) Class.forName(
              String.format("ooga.logic.board.Pieces.SpotCollection.%s", getPieceProperties().
                  getString(String.format("movement%s",getTeamIfNecessary())))).getConstructor().newInstance()
      );


    }
    catch (Exception e){
      setDefaultMovement(pieceToString);
    }
  }

  private String getTeamIfNecessary(){
    if(teamMatters){
      return getTeam() + "";
    }
    return "";

  }

  private void setDefaultMovement(String pieceToString) {
    try{
      setMyMovement(
          (SpotCollection) Class.forName(
              String.format("%s%s",pieceToString,"Movement")
          ).getConstructor().newInstance()
      );

    }
    catch(Exception e){
      e.printStackTrace();
    }
  }


  private void setCapture(String pieceToString) {
    try{
      setMyCapture(
          (SpotCollection) Class.forName(
              String.format("ooga.logic.board.Pieces.SpotCollection.%s", getPieceProperties().
                  getString(String.format("capture%s",getTeamIfNecessary())))).getConstructor().newInstance()
      );
    }
    catch (Exception e){
      setDefaultCapture(pieceToString);
    }
  }

  private void setDefaultCapture(String pieceToString) {
    try{
      setMyCapture(
          (SpotCollection) Class.forName(
              String.format("ooga.logic.board.Pieces.SpotCollection.%s%s",pieceToString,"Movement")
          ).getConstructor().newInstance()
      );

    }
    catch(Exception e){
      e.printStackTrace();
    }
  }

  @Override
  public void setCheckable(boolean checkable) {
    this.isCheckable = checkable;
  }

  @Override
  public boolean getCheckable() {
    return isCheckable;
  }

}
