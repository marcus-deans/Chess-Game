package ooga.logic.board.Pieces.PieceBundle.BooleanStorageClasses;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * Calculate and store whether a piece has 'atomicImmunity' or not
 * @author Amr Tagel-Din
 */
public class atomicImmunityStorage extends BooleanStorage {
  private static final String MY_STRING = "atomicImmunity";

  public atomicImmunityStorage(Map<String, String> attributeMap, ResourceBundle pieceProperties, ResourceBundle defaultProperties) {
    super(MY_STRING, attributeMap,pieceProperties,defaultProperties);
  }



}
