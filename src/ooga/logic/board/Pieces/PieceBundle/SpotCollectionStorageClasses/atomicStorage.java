package ooga.logic.board.Pieces.PieceBundle.SpotCollectionStorageClasses;

import java.util.Map;
import java.util.ResourceBundle;
import ooga.logic.board.Pieces.SpotCollection.KingMovement;

public class atomicStorage extends SpotCollectionStorage {
  private static final String ATOMIC = "atomicRadius";

  public atomicStorage(String myString, Map<String, String> attributeMap, ResourceBundle pieceProperties,
      ResourceBundle defaultProperties, String teamMatters) {
    super(myString, attributeMap, pieceProperties, defaultProperties, teamMatters);
  }

  protected String getMySpotType(){
    return ATOMIC;
  }

  protected void movementOrNone() {
    getDefaultState();
  }

  protected void getDefaultState(){
    try {
      setMySpotCollection(
          getSpotCollectionFromBundle(getDefaultProperties())
      );
    }
    catch (Exception e){
      setMySpotCollection(new KingMovement(getWidthHeightMax()));
    }
  }

}
