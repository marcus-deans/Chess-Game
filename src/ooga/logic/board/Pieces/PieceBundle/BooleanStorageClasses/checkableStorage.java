package ooga.logic.board.Pieces.PieceBundle.BooleanStorageClasses;

import java.util.Map;
import java.util.ResourceBundle;


/**
 * Calculate and store whether a piece is checkable
 * @author Amr Tagel-Din
 */
public class checkableStorage extends BooleanStorage {
  private static final String MY_STRING = "isCheckable";

  public checkableStorage(Map<String, String> attributeMap, ResourceBundle pieceProperties, ResourceBundle defaultProperties) {
    super(MY_STRING, attributeMap,pieceProperties,defaultProperties);
  }



}
