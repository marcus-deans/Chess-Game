package ooga.logic.board.Pieces.PieceBundle.SpotCollectionStorageClasses;

import java.util.Map;
import java.util.ResourceBundle;

public class atomicStorage extends SpotCollectionStorage {
  private static final String ATOMIC = "atomic";

  public atomicStorage(String myString, Map<String, String> attributeMap, ResourceBundle pieceProperties,
      ResourceBundle defaultProperties, String teamMatters) {
    super(myString, attributeMap, pieceProperties, defaultProperties, teamMatters);
  }

  /**
   * @return The string we are looking for in the keys
   */
  protected String getMySpotType(){
    return ATOMIC;
  }

  protected void movementOrNone() {
    getDefaultState();
  }
}
