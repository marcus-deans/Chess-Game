package ooga.logic.board.Pieces.PieceBundle;

import java.util.Map;
import java.util.ResourceBundle;
import ooga.logic.board.Pieces.Interfaces.*;
import ooga.logic.board.Pieces.PieceBundle.BooleanStorageClasses.*;
import ooga.logic.board.Pieces.PieceBundle.SpotCollectionStorageClasses.*;
import ooga.logic.board.Pieces.PieceCollection.DefaultPromotionPieces;
import ooga.logic.board.Pieces.PieceCollection.PieceCollection;
import ooga.logic.board.Pieces.SpotCollection.KingMovement;
import ooga.logic.board.Pieces.SpotCollection.LastRankSpots;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;
import ooga.logic.board.coordinate.Coordinate;

abstract public class Piece implements PieceLogic, MoveLogic, CaptureLogic, PromoteLogic{
  private Coordinate myCoordinate;
  private SpotCollection areaOfEffect;
  private SpotCollection myPromotionSpots;
  private PieceCollection myPromotionOptions;
  private int team;
  private int pieceValue;

  private String PieceName;
  private Map<String,String> attributeMap;

  private ResourceBundle PieceProperties;
  private ResourceBundle DefaultProperties;

  private static final String PIECES_PACKAGE = Piece.class.getPackageName() + ".resources.";
  private static final String DEFAULT_TO_STRING = "Default";
  private static final String EMPTY = "";

  private static final String PROMOTION = "promotion";

  private BooleanStorage teamMatters;
  private BooleanStorage myJump;
  private BooleanStorage canCannibalize;
  private BooleanStorage checkable;
  private SpotCollectionStorage myCaptureStorage;
  private SpotCollectionStorage myMovementStorage;


  public Piece(String pieceToString, int team, Coordinate myCoordinate, Map<String,String> myAttributeMap) {
    setPieceName(pieceToString);
    setMyAttributeMap(myAttributeMap);

    setPieceProperties(pieceToString);
    setDefaultProperties();
    setTeam(team);
    teamMatters = new TeamMattersStorage(attributeMap,PieceProperties,DefaultProperties);
    myJump = new JumpStorage(attributeMap,PieceProperties,DefaultProperties);
    canCannibalize = new cannibalizeStorage(attributeMap,PieceProperties,DefaultProperties);
    checkable= new checkableStorage(attributeMap,PieceProperties,DefaultProperties);
    myCaptureStorage = new captureStorage(pieceToString, attributeMap,PieceProperties,DefaultProperties,getTeamIfNecessary());
    myMovementStorage = new movementStorage(pieceToString, attributeMap,PieceProperties,DefaultProperties,getTeamIfNecessary());
    setCoordinate(myCoordinate);
    setPromotionSpots();

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


  @Override
  public Coordinate getCoordinate(){
    return myCoordinate;
  }

  @Override
  public void setCoordinate(Coordinate passedCoordinate) {
    myCoordinate = passedCoordinate;
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
    return myCaptureStorage.getSpotCollection();
  }

  @Override
  public SpotCollection getPossibleMoves() {
    return myMovementStorage.getSpotCollection();
  }

//  protected void setMyMovement(SpotCollection movementToSet){
//    myMovement = movementToSet;
//  }




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
    return myJump.getValue();
  }

  private String getTeamIfNecessary(){
    return (teamMatters.getValue()) ? getTeam() + EMPTY : EMPTY;

  }


  @Override
  public void setCheckable(boolean checkable) {

  }

  @Override
  public boolean getCheckable() {
    return checkable.getValue();
  }

  private String capitalizeFirst(String toBeCapitalized){
    return toBeCapitalized.substring(0, 1).toUpperCase() + toBeCapitalized.substring(1);
  }

  @Override
  public void setCanCannibalize(boolean cannibalize) {

  }

  @Override
  public boolean canCannibalize() {
    return canCannibalize.getValue();
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
