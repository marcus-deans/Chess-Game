package ooga.logic.board.Pieces.PieceBundle;

import java.util.Map;
import java.util.ResourceBundle;
import ooga.logic.board.Pieces.Interfaces.CaptureLogic;
import ooga.logic.board.Pieces.Interfaces.MoveLogic;
import ooga.logic.board.Pieces.Interfaces.PieceLogic;
import ooga.logic.board.Pieces.Interfaces.PromoteLogic;
import ooga.logic.board.Pieces.PieceBundle.VariableStorageClasses.JumpStorage;
import ooga.logic.board.Pieces.PieceBundle.VariableStorageClasses.BooleanStorage;
import ooga.logic.board.Pieces.PieceBundle.VariableStorageClasses.TeamMattersStorage;
import ooga.logic.board.Pieces.PieceBundle.VariableStorageClasses.cannibalizeStorage;
import ooga.logic.board.Pieces.PieceBundle.VariableStorageClasses.checkableStorage;
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

  private String PieceName;
  private Map<String,String> attributeMap;

  private ResourceBundle PieceProperties;
  private ResourceBundle DefaultProperties;

  private static final String PIECES_PACKAGE = Piece.class.getPackageName() + ".resources.";
  private static final String DEFAULT_TO_STRING = "Default";
  private static final String EMPTY = "";

  private static final String SPOT_COLLECTION_BASE = SpotCollection.class.getPackageName();
  private static final String PROMOTION = "promotion";
  private static final String MOVEMENT = "movement";
  private static final String CAPTURE = "capture";

  private BooleanStorage teamMatters;
  private BooleanStorage myJump;
  private BooleanStorage canCannibalize;
  private BooleanStorage checkable;


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
    setCoordinate(myCoordinate);
    setPromotionSpots();

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
    return myJump.getValue();
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
    if(teamMatters.getValue()){
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

  }

  @Override
  public boolean getCheckable() {
    return checkable.getValue();
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
