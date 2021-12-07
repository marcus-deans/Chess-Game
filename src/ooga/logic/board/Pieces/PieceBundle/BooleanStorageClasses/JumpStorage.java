package ooga.logic.board.Pieces.PieceBundle.BooleanStorageClasses;

import java.util.Map;
import java.util.ResourceBundle;
/**
 * Calculate and store whether a piece can jump over pieces of the same type
 * @author Amr Tagel-Din
 */
public class JumpStorage extends BooleanStorage {
  private static final String MY_STRING = "jump";

  public JumpStorage(Map<String, String> attributeMap, ResourceBundle pieceProperties, ResourceBundle defaultProperties) {
    super(MY_STRING, attributeMap,pieceProperties,defaultProperties);
  }





}
