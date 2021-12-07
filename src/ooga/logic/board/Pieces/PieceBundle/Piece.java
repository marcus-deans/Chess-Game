package ooga.logic.board.Pieces.PieceBundle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import ooga.logic.board.Pieces.Interfaces.PieceLogic;
import ooga.logic.board.Pieces.PieceBundle.BooleanStorageClasses.*;
import ooga.logic.board.Pieces.PieceBundle.SpotCollectionStorageClasses.*;
import ooga.logic.board.Pieces.PieceCollection.DefaultPromotionPieces;
import ooga.logic.board.Pieces.PieceCollection.PieceCollection;
import ooga.logic.board.Pieces.SpotCollection.KingMovement;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;
import ooga.logic.board.coordinate.Coordinate;

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

  private static final String PIECES_PACKAGE = Piece.class.getPackageName() + ".resources.";
  private static final String DEFAULT_TO_STRING = "Default";
  private static final String EMPTY = "";

  private static final String PROMOTION = "promotion";
  private static final String ALL = "all";


  private BooleanStorage teamMatters;
  private BooleanStorage myJump;
  private BooleanStorage canCannibalize;
  private BooleanStorage checkable;
  private BooleanStorage atomicImmunity;
  private SpotCollectionStorage myCaptureStorage;
  private SpotCollectionStorage myMovementStorage;
  private SpotCollectionStorage myAtomicStorage;


  public Piece(String pieceToString, int team, Coordinate myCoordinate, Map<String,String> myAttributeMap) {
    setPieceName(pieceToString);
    setMyAttributeMap(myAttributeMap);

    setPieceProperties(pieceToString);
    setDefaultProperties();
    setTeam(team);

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
  }

  private void setJump() {
    myJump = new JumpStorage(attributeMap,PieceProperties,DefaultProperties);
  }

  private void setCanCannibalize() {
    canCannibalize = new cannibalizeStorage(attributeMap,PieceProperties,DefaultProperties);
  }

  private void setAtomicImmunity(){
    atomicImmunity = new atomicImmunityStorage(attributeMap,PieceProperties,DefaultProperties);
  }

  private void setCheckable() {
    checkable= new checkableStorage(attributeMap,PieceProperties,DefaultProperties);
  }

  private void setCapture() {
    myCaptureStorage = new captureStorage(PieceName, attributeMap,PieceProperties,DefaultProperties,getTeamIfNecessary());
  }

  private void setMovement() {
    myMovementStorage = new movementStorage(PieceName, attributeMap,PieceProperties,DefaultProperties,getTeamIfNecessary());
  }

  private void setTeamMatters() {
    teamMatters = new TeamMattersStorage(attributeMap,PieceProperties,DefaultProperties);
  }

  private void setMyAttributeMap(Map<String, String> myAttributeMap){
    attributeMap = new HashMap<>();
    for (String myType : myAttributeMap.keySet()){
      int locationOfLine= myType.indexOf("|");
      String type = myType.substring(0,locationOfLine);
      String attrKey =  myType.substring(locationOfLine + 1);
      if (type.equalsIgnoreCase(PieceName) || type.equalsIgnoreCase(ALL)){
        String result = myAttributeMap.get(myType);
        attributeMap.put(attrKey,result);
      }
    }
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


  private void setPieceProperties(String PIECE_AS_STRING) {
    PieceProperties=ResourceBundle.getBundle(PIECES_PACKAGE+PIECE_AS_STRING);
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
//      setMyPromotionSpots(new LastRankSpots(new DefaultPromotionPieces()));
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
    return ListContainsCoordinate(getPossibleCaptures(),captureCoordinate);
  }

 // @Override
  public boolean canMoveTo(Coordinate captureCoordinate) {
    return ListContainsCoordinate(getPossibleMoves(),captureCoordinate);
  }

  private boolean ListContainsCoordinate(SpotCollection CoordList,Coordinate myCoord){
    for (List<Coordinate> x : CoordList.getPossibleSpots(myCoordinate)){
      if (x.contains(myCoord)) return true;
    }
    return false;
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
  public boolean getCheckable() {
    return checkable.getValue();
  }

  @Override
  public boolean canCannibalize() {
    return canCannibalize.getValue();
  }

  @Override
  public void updateRules(Map<String, String> myMap) {
    attributeMap = myMap;
    teamMatters.update(attributeMap);
    myJump.update(attributeMap);
    canCannibalize.update(attributeMap);
    checkable.update(attributeMap);
    myCaptureStorage.update(attributeMap);
    myMovementStorage.update(attributeMap);
    myAtomicStorage.update(attributeMap);
  }

  public SpotCollection getAtomicArea(){
    return myAtomicStorage.getSpotCollection();
  }

  public boolean getAtomicIMmunity(){
    return atomicImmunity.getValue();
  }

}
