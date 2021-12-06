package ooga.logic.board.Pieces.PieceBundle.SpotCollectionStorageClasses;

import java.util.Map;
import java.util.ResourceBundle;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;

abstract public class SpotCollectionStorage {
  private Boolean myVar;
  private String MY_STRING;
  private static final String MOVEMENT = "movement";
  private SpotCollection mySpotCollection;


  public SpotCollectionStorage(String myString, Map<String, String> attributeMap, ResourceBundle pieceProperties, ResourceBundle defaultProperties) {
//    MY_STRING = myString;
//    calculateVariable(attributeMap,pieceProperties,defaultProperties);
  }


  private void setMySpotCollection(SpotCollection mySpotCollection){
    mySpotCollection = mySpotCollection;
  }

  public SpotCollection getSpotCollection(){
    return mySpotCollection;
  }

  protected String capitalizeFirst(String toBeCapitalized){
    return toBeCapitalized.substring(0, 1).toUpperCase() + toBeCapitalized.substring(1);
  }

}
