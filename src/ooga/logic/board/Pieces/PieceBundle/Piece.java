package ooga.logic.board.Pieces.PieceBundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import ooga.logic.board.Pieces.PieceBundle.BooleanStorageClasses.*;
import ooga.logic.board.Pieces.PieceBundle.SpotCollectionStorageClasses.*;
import ooga.logic.board.Pieces.PieceCollection.PieceCollection;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;
import ooga.logic.board.coordinate.Coordinate;


/**
 * Generic class to hold a default yet flexible piece; stores basic information about itself but relies
 * on helper storage classes to deciper information regarding its booleans and spotCollections
 * @author Amr Tagel-Din
 */
abstract public class Piece implements PieceLogic{
  private Coordinate myCoordinate;
  private SpotCollection myPromotionSpots;
  private PieceCollection myPromotionOptions;
  private int team;
  private int pieceValue;

  private String PieceName;
  private Map<String,String> attributeMap;

  private ResourceBundle PieceProperties;
  private ResourceBundle DefaultProperties;

  private static final String PIECES_PACKAGE = "ooga.logic.board.Pieces.PieceBundle.resources.";
  private static final String DEFAULT_TO_STRING = "Default";
  private static final String EMPTY = "";

  private static final String PROMOTION = "promotion";
  private static final String ALL = "all";
  private static final String SPLIT = "|";


  private BooleanStorage teamMatters;
  private BooleanStorage myJump;
  private BooleanStorage canCannibalize;
  private BooleanStorage checkable;
  private BooleanStorage atomicImmunity;
  private SpotCollectionStorage myCaptureStorage;
  private SpotCollectionStorage myMovementStorage;
  private SpotCollectionStorage myAtomicStorage;
  private List<BooleanStorage> booleanStorageList;
  private List<SpotCollectionStorage> SpotCollectionStorageList;

  /**
   * Create the piece and initialize its values
   * @param pieceToString name of the piece
   * @param team team the piece is on
   * @param myCoordinate coordinate the piece is on
   * @param myAttributeMap rules/values that the piece abides by before its default values
   */
  public Piece(String pieceToString, int team, Coordinate myCoordinate, Map<String,String> myAttributeMap) {
    setPieceName(pieceToString);
    setMyAttributeMap(myAttributeMap);

    setPieceProperties(pieceToString);
    setDefaultProperties();
    setTeam(team);
    booleanStorageList = new ArrayList<>();
    SpotCollectionStorageList = new ArrayList<>();
    setTeamMatters();
    setJump();
    setCanCannibalize();
    setCheckable();
    setAtomicImmunity();
    setCapture();
    setMovement();
    setAtomic();

    setCoordinate(myCoordinate);
    setPromotionSpots();
  }



  private void setAtomic(){
    myAtomicStorage = new atomicStorage(PieceName,attributeMap,PieceProperties,DefaultProperties,getTeamIfNecessary());
    SpotCollectionStorageList.add(myAtomicStorage);
  }

  private void setJump() {
    myJump = new JumpStorage(attributeMap,PieceProperties,DefaultProperties);
    booleanStorageList.add(myJump);
  }

  private void setCanCannibalize() {
    canCannibalize = new cannibalizeStorage(attributeMap,PieceProperties,DefaultProperties);
    booleanStorageList.add(canCannibalize);
  }

  private void setAtomicImmunity(){
    atomicImmunity = new atomicImmunityStorage(attributeMap,PieceProperties,DefaultProperties);
    booleanStorageList.add(atomicImmunity);
  }

  private void setCheckable() {
    checkable= new checkableStorage(attributeMap,PieceProperties,DefaultProperties);
    booleanStorageList.add(checkable);
  }

  private void setCapture() {
    myCaptureStorage = new captureStorage(PieceName, attributeMap,PieceProperties,DefaultProperties,getTeamIfNecessary());
    SpotCollectionStorageList.add(myCaptureStorage);
  }

  private void setMovement() {
    myMovementStorage = new movementStorage(PieceName, attributeMap,PieceProperties,DefaultProperties,getTeamIfNecessary());
    SpotCollectionStorageList.add(myMovementStorage);
  }

  private void setTeamMatters() {
    teamMatters = new TeamMattersStorage(attributeMap,PieceProperties,DefaultProperties);
    booleanStorageList.add(teamMatters);
  }

  private void setMyAttributeMap(Map<String, String> myAttributeMap){
    attributeMap = new HashMap<>();
    for (String myType : myAttributeMap.keySet()){
      int locationOfLine= myType.indexOf(SPLIT);
      String type = beforeSplit(myType, locationOfLine);
      String attrKey = afterSplit(myType, locationOfLine);
      if (type.equalsIgnoreCase(PieceName) || type.equalsIgnoreCase(ALL)){
        String result = myAttributeMap.get(myType);
        attributeMap.put(attrKey,result);
      }
    }
  }

  private String afterSplit(String myType, int locationOfLine) {
    return myType.substring(locationOfLine + 1);
  }

  private String beforeSplit(String myType, int locationOfLine) {
    return myType.substring(0, locationOfLine);
  }

  private void setPieceName(String pieceToString){
    PieceName = pieceToString;
  }

  /**
   * @return the type of a piece
   */
  @Override
  public String getPieceName(){
    return PieceName;
  }

  /**
   * @param otherPiece the other piece we're comparing
   * @return if two pieces are of the same type
   */
  @Override
  public boolean equals(Piece otherPiece){
    return this.PieceName == otherPiece.getPieceName();
  }


  private void setPieceProperties(String PIECE_AS_STRING) {
    PieceProperties=ResourceBundle.getBundle(PIECES_PACKAGE+PIECE_AS_STRING);
  }

  private void setDefaultProperties() {
    DefaultProperties=ResourceBundle.getBundle(PIECES_PACKAGE+DEFAULT_TO_STRING);
  }

  /**
   * @return the coordinate a piece is currently at
   */
  @Override
  public Coordinate getCoordinate(){
    return myCoordinate;
  }

  /**
   * change a coordinate to a new coordinate
   * @param passedCoordinate the new coordinate the piece will be at
   */
  @Override
  public void setCoordinate(Coordinate passedCoordinate) {
    myCoordinate = passedCoordinate;
  }

  protected void setPromotionSpots(){
    if (PieceProperties.containsKey(PROMOTION)){
       //setMyPromotionSpots(new LastRankSpots());
    }
  }

  /**
   * @return the squares a piece can promote
   */
  @Override
  public SpotCollection promotionSquares() {
    return myPromotionSpots;
  }


  /**
   * @param captureCoordinate the coordinate to be captured
   * @return if that coordinate can be captured
   */
  @Override
  public boolean canCapture(Coordinate captureCoordinate) {
    return ListContainsCoordinate(getPossibleCaptures(),captureCoordinate);
  }

  /**
   * @param moveCoordinate the coordinate to be moved to
   * @return if the piece can move to a given coordinate
   */
  @Override
  public boolean canMoveTo(Coordinate moveCoordinate) {
    return ListContainsCoordinate(getPossibleMoves(),moveCoordinate);
  }

  private boolean ListContainsCoordinate(SpotCollection CoordList,Coordinate myCoord){
    for (List<Coordinate> x : CoordList.getPossibleSpots(myCoordinate)){
      if (x.contains(myCoord)) return true;
    }
    return false;
  }


  /**
   * @return the possible promotion Options of a piece (the other pieces it can become)
   */
  @Override
  public PieceCollection possiblePromotionPieces(){
    return myPromotionOptions;
  }

  /**
   * @return the Collection of spots that can be captured
   */
  @Override
  public SpotCollection getPossibleCaptures() {
    return myCaptureStorage.getSpotCollection();
  }

  /**
   * @return the collection of spots that you can move to
   */
  @Override
  public SpotCollection getPossibleMoves() {
    return myMovementStorage.getSpotCollection();
  }

  /**
   * set a piece's team to a new team
   * @param newTeam team to set a piece
   */
  @Override
  public void setTeam(int newTeam) {
    this.team = newTeam;
  }

  /**
   * @return the team a piece is currently a part of
   */
  @Override
  public int getTeam() {
    return team;
  }

  /**
   * change the value of a piece
   * @param value new value (like cost, valuableness) of a piece
   */
  @Override
  public void setValue(int value) {
    pieceValue = value;
  }

  /**
   * @return the value of a piece
   */
  @Override
  public int getValue() {
    return pieceValue;
  }

  /**
   * @return if a piece can jump
   */
  @Override
  public boolean getCanJump() {
    return myJump.getValue();
  }

  private String getTeamIfNecessary(){
    return (teamMatters.getValue()) ? getTeam() + EMPTY : EMPTY;

  }

  /**
   * @return if a piece is checkable
   */
  @Override
  public boolean getCheckable() {
    return checkable.getValue();
  }

  /**
   * @return if a piece can cannibalize
   */
  @Override
  public boolean canCannibalize() {
    return canCannibalize.getValue();
  }

  /**
   * update the rules of the given piece Storage units
   * @param myMap new map of rules to update each piece to conform to
   */
  @Override
  public void updateRules(Map<String, String> myMap) {

    attributeMap = myMap;

    for (BooleanStorage myBoolStorage : booleanStorageList){
      myBoolStorage.update(attributeMap);
    }

    for (SpotCollectionStorage mySpotStorage : SpotCollectionStorageList){
      mySpotStorage.update(attributeMap);
    }

  }

  /**
   * @return the atomicArea that will be captured upon a piece's death
   */
  @Override
  public SpotCollection getAtomicArea(){
    return myAtomicStorage.getSpotCollection();
  }

  /**
   * @return if a piece is immune to a atomic blast radius
   */
  @Override
  public boolean getAtomicImmunity(){
    return atomicImmunity.getValue();
  }

  /**
   * @param promotionToSet promotion spots of a piece
   */
  @Override
  public void setMyPromotionSpots(SpotCollection promotionToSet){
    myPromotionSpots = promotionToSet;
  }

}
