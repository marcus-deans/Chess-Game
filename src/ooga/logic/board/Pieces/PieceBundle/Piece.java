package ooga.logic.board.Pieces.PieceBundle;

import java.util.List;
import java.util.ResourceBundle;
import ooga.logic.board.Pieces.Interfaces.CaptureLogic;
import ooga.logic.board.Pieces.Interfaces.MoveLogic;
import ooga.logic.board.Pieces.Interfaces.PieceLogic;
import ooga.logic.board.Pieces.Interfaces.PromoteLogic;
import ooga.logic.board.Pieces.PieceCollection.DefaultPromotionPieces;
import ooga.logic.board.Pieces.PieceCollection.PieceCollection;
import ooga.logic.board.Pieces.SpotCollection.KingMovement;
import ooga.logic.board.Pieces.SpotCollection.LastRankSpots;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;
import ooga.logic.board.coordinate.Coordinate;

abstract public class Piece implements PieceLogic, MoveLogic, CaptureLogic, PromoteLogic{
  private Coordinate myCoordinate;
  private SpotCollection myMovement;
  private SpotCollection myCapture;
  private SpotCollection areaOfEffect;
  private SpotCollection myPromotionSpots;
  private PieceCollection myPromotionOptions;
  private int team;
  private int pieceValue;
  private boolean canJump;
  private boolean isCheckable;
  private boolean canCannibalize;

  private ResourceBundle PieceProperties;
  private ResourceBundle DefaultProperties;

  private static final String PIECES_PACKAGE = Piece.class.getPackageName() + ".resources.";
  private static final String DEFAULT_TO_STRING = "Default";
  private static final String EMPTY = "";

  private static final String SPOT_COLLECTION_BASE = SpotCollection.class.getPackageName();
  private static final String TEAM_MATTERS = "teamMatters";
  private static final String PROMOTION = "promotion";
  private static final String JUMP = "jump";
  private static final String IS_CHECKABLE = "isCheckable";
  private static final String MOVEMENT = "movement";
  private static final String CAPTURE = "capture";
  private static final String CANNIBALIZE = "canCannibalize";

  private boolean teamMatters;

  public Piece(String pieceToString, int team, Coordinate myCoordinate) {
    setPieceProperties(pieceToString);
    setDefaultProperties();
    setMyTeamMatters();
    setTeam(team);
    setCoordinate(myCoordinate);
    setJump();
    setCannibalize();
    setMyTeamMatters();
    setMovement(pieceToString);
    setCapture(pieceToString);
    setPromotionSpots();
    setMyCheckable();
  }

  private void setMyTeamMatters() {
    try{
      setTeamMatters(Boolean.parseBoolean(getPieceProperties().getString(TEAM_MATTERS)));
    }
    catch (Exception e){
      setTeamMatters(Boolean.parseBoolean(getDefaultProperties().getString(TEAM_MATTERS)));
    }
  }

  private void setTeamMatters(boolean value){
    teamMatters = value;
  }

  protected void setPromotionSpots(){
    if (PieceProperties.containsKey(PROMOTION)){
      setMyPromotionSpots(new LastRankSpots(new DefaultPromotionPieces()));
    }
  }

  private void setJump() {
    try{
      setCanJump(Boolean.parseBoolean(getPieceProperties().getString(JUMP)));
    }
    catch (Exception e){
      setCanJump(Boolean.parseBoolean(getDefaultProperties().getString(JUMP)));
    }
  }

  private void setMyCheckable() {
    try{
      setCanJump(Boolean.parseBoolean(getPieceProperties().getString(IS_CHECKABLE)));
    }
    catch (Exception e){
      setCanJump(Boolean.parseBoolean(getDefaultProperties().getString(IS_CHECKABLE)));
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
    return myCapture;
  }

  @Override
  public SpotCollection getPossibleMoves() {
    return myMovement;
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
    return myPromotionSpots;
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
    try{
      setMyMovement(
          (SpotCollection) Class.forName(
              String.format("%s.%s",SPOT_COLLECTION_BASE,getPieceProperties().
                  getString(String.format("%s%s",MOVEMENT,getTeamIfNecessary())))).getConstructor().newInstance()
      );


    }
    catch (Exception e){
      setDefaultMovement(pieceToString);
    }
  }

  private String getTeamIfNecessary(){
    if(teamMatters){
      return getTeam() + EMPTY;
    }
    return EMPTY;

  }

  private void setDefaultMovement(String pieceToString) {
    try{
      setMyMovement(
          (SpotCollection) Class.forName(
              String.format("%s%s",pieceToString,capitalizeFirst(MOVEMENT))
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
              String.format("%s.%s",SPOT_COLLECTION_BASE ,getPieceProperties().
                  getString(String.format("%s%s",CAPTURE,getTeamIfNecessary())))).getConstructor().newInstance()
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
              String.format("%s.%s%s",SPOT_COLLECTION_BASE,pieceToString,capitalizeFirst(MOVEMENT))
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

  private String capitalizeFirst(String toBeCapitalized){
    return toBeCapitalized.substring(0, 1).toUpperCase() + toBeCapitalized.substring(1);
  }

  private void setCannibalize() {
    try{
      setCanCannibalize(Boolean.parseBoolean(getPieceProperties().getString(CANNIBALIZE)));
    }
    catch (Exception e){
      setCanCannibalize(Boolean.parseBoolean(getDefaultProperties().getString(CANNIBALIZE)));
    }
  }

  @Override
  public void setCanCannibalize(boolean cannibalize) {
    canCannibalize = cannibalize;
  }

  @Override
  public boolean canCannibalize() {
    return canCannibalize;
  }

/*
TEMPORARY
 */
  public SpotCollection getAtomicArea(){
    return areaOfEffect;
  }
  public void setAtomicArea(){
    areaOfEffect = new KingMovement();
  }

}
