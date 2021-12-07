package ooga.logic.board.Pieces.PieceBundle.BooleanStorageClasses;

import java.util.Map;
import java.util.ResourceBundle;
/**
 * Calculate and store whether a piece can jump over pieces of the same type
 * @author Amr Tagel-Din
 */
public class JumpStorage extends BooleanStorage {
  private static final String MY_STRING = "jump";

  /**
   * Pass properties back to calculate the jump property for this piece
   * @param attributeMap Added Map of properties that will give the pieces quick flexibility
   * @param pieceProperties Properties of the specific piece we have chosen
   * @param defaultProperties Default properties for a piece if the pieces hasn't been given
   *                          a property
   */
  public JumpStorage(Map<String, String> attributeMap, ResourceBundle pieceProperties, ResourceBundle defaultProperties) {
    super(MY_STRING, attributeMap,pieceProperties,defaultProperties);
  }





}
