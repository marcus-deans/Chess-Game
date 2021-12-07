package ooga.logic.board.Pieces.PieceBundle.BooleanStorageClasses;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * Calculate and store whether a piece has 'atomicImmunity' or not
 * @author Amr Tagel-Din
 */
public class atomicImmunityStorage extends BooleanStorage {
  private static final String MY_STRING = "atomicImmunity";

  /**
   * Pass properties back to calculate the atomicImmunity property for this piece
   * @param attributeMap Added Map of properties that will give the pieces quick flexibility
   * @param pieceProperties Properties of the specific piece we have chosen
   * @param defaultProperties Default properties for a piece if the pieces hasn't been given
   *                          a property
   */
  public atomicImmunityStorage(Map<String, String> attributeMap, ResourceBundle pieceProperties, ResourceBundle defaultProperties) {
    super(MY_STRING, attributeMap,pieceProperties,defaultProperties);
  }



}
