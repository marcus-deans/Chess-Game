package ooga.logic.board.Pieces.PieceBundle.BooleanStorageClasses;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * Calculate and store whether or not the team of a piece matters
 * @author Amr Tagel-Din
 */
public class TeamMattersStorage extends BooleanStorage {
  private static final String MY_STRING = "teamMatters";

  public TeamMattersStorage(Map<String, String> attributeMap, ResourceBundle pieceProperties, ResourceBundle defaultProperties) {
    super(MY_STRING, attributeMap,pieceProperties,defaultProperties);
  }



}
