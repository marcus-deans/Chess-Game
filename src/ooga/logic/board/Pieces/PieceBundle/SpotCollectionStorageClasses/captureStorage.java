package ooga.logic.board.Pieces.PieceBundle.SpotCollectionStorageClasses;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * Store the spotCollection explaining the capture logic of a specific piece (ie does this
 * piece eat like a pawn? a king? etc.)
 * @author Amr Tagel-Din
 */
public class captureStorage extends SpotCollectionStorage {
  private static final String CAPTURE = "capture";

  /**
   * Scan all the sources of information below for the atomic nature of this given piece
   * @param myString String that we want to scan the attribute map for
   * @param attributeMap map of keys and values that define the pieces outside of the default;
   *                     allow for flexible pieces
   * @param pieceProperties default properties of this particular piece
   * @param defaultProperties default properties of any piece assuming that the property is not
   *                          available in the piece properties
   * @param teamMatters either an empty variable, or the team number depending on if the team affects
   *                    the manner in which the piece moves (ex: white pawns vs black pawns)
   */
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
