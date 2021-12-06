package ooga.logic.board.Pieces.PieceBundle;

import java.util.List;
import java.util.Map;
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

  private String PieceName;
  private Map<String,String> attributeMap;

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

  public Piece(String pieceToString, int team, Coordinate myCoordinate, Map<String,String> myAttributeMap) {
    setPieceName(pieceToString);
    setMyAttributeMap(myAttributeMap);

    setPieceProperties(pieceToString);
    setDefaultProperties();
    setTeam(team);
    setMyTeamMatters();
    setCoordinate(myCoordinate);
    setJump();
    setCannibalize();
    setPromotionSpots();
    setMyCheckable();

    setMovement(pieceToString);
    setCapture(pieceToString);
  }

  private void setMyAttributeMap(Map<String, String> myAttributeMap){
    attributeMap = myAttributeMap;
  }

  private void setPieceName(String pieceToString){
    PieceName = pieceToString;
  }

  public String getPieceName(){
    return PieceName;
  }

  public boolean equals(Piece otherPiece){
    return this.PieceName == otherPiece.getPieceName();
  }


  private ResourceBundle getPieceProperties() {
    return PieceProperties;
  }

  private void setPieceProperties(String PIECE_AS_STRING) {
    PieceProperties=ResourceBundle.getBundle(PIECES_PACKAGE+PIECE_AS_STRING);
  }

  private ResourceBundle getDefaultProperties() {
    return DefaultProperties;
  }

  private void setDefaultProperties() {
    DefaultProperties=ResourceBundle.getBundle(PIECES_PACKAGE+DEFAULT_TO_STRING);
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


  @Override
  public Coordinate getCoordinate(){
    return myCoordinate;
  }

  @Override
  public void setCoordinate(Coordinate passedCoordinate) {
    myCoordinate = passedCoordinate;
  }





  private void setJump() {
    try{
      setCanJump(Boolean.parseBoolean(getPieceProperties().getString(JUMP)));
    }
    catch (Exception e){
      setCanJump(Boolean.parseBoolean(getDefaultProperties().getString(JUMP)));
    }
  }

  @Override
  public void setCanJump(boolean newJump) {
    canJump = newJump;
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


  protected void setPromotionSpots(){
    if (PieceProperties.containsKey(PROMOTION)){
      setMyPromotionSpots(new LastRankSpots(new DefaultPromotionPieces()));
    }
  }

  protected void setMyPromotionSpots(SpotCollection promotionToSet){
    myPromotionSpots = promotionToSet;
  }

  @Override
  public SpotCollection promotionSquares() {
    return myPromotionSpots;
  }

//  protected void setMyPromotionPieces(PieceCollection myPieceCollection) {
//    myPromotionOptions = myPieceCollection;
//  }


  private void setMyCheckable() {
    try{
      setCheckable(Boolean.parseBoolean(getPieceProperties().getString(IS_CHECKABLE)));
    }
    catch (Exception e){
      setCheckable(Boolean.parseBoolean(getDefaultProperties().getString(IS_CHECKABLE)));
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



  @Override
  public boolean canCapture(Coordinate captureCoordinate) {
    return getPossibleCaptures().getPossibleSpots(myCoordinate).contains(captureCoordinate);
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


  private String capitalizeFirst(String toBeCapitalized){
    return toBeCapitalized.substring(0, 1).toUpperCase() + toBeCapitalized.substring(1);
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
