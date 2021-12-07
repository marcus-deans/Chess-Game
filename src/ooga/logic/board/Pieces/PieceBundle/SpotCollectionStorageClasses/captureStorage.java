package ooga.logic.board.Pieces.PieceBundle.SpotCollectionStorageClasses;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * Store the spotCollection explaining the capture logic of a specific piece (ie does this
 * piece eat like a pawn? a king? etc.)
 */
public class captureStorage extends SpotCollectionStorage {
  private static final String CAPTURE = "capture";

  public captureStorage(String myString, Map<String, String> attributeMap, ResourceBundle pieceProperties,
      ResourceBundle defaultProperties, String teamMatters) {
    super(myString, attributeMap, pieceProperties, defaultProperties, teamMatters);
  }

  /**
   * @return The string we are looking for in the keys/ in the spot collection titles
   */
  protected String getMySpotType(){
    return CAPTURE;
  }

}
