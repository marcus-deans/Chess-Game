package ooga.logic.board.Pieces.PieceBundle.SpotCollectionStorageClasses;

import java.util.Map;
import java.util.ResourceBundle;

public class captureStorage extends SpotCollectionStorage {
  private static final String CAPTURE = "capture";

  public captureStorage(String myString, Map<String, String> attributeMap, ResourceBundle pieceProperties,
      ResourceBundle defaultProperties, String teamMatters) {
    super(myString, attributeMap, pieceProperties, defaultProperties, teamMatters);
  }

  protected String getMySpotType(){
    return CAPTURE;
  }

}
