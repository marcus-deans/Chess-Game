package ooga.logic.board.Pieces.PieceBundle.VariableStorageClasses;

import java.util.Map;
import java.util.ResourceBundle;

public class JumpStorage extends BooleanStorage {
  private static final String MY_STRING = "jump";

  public JumpStorage(Map<String, String> attributeMap, ResourceBundle pieceProperties, ResourceBundle defaultProperties) {
    super(MY_STRING, attributeMap,pieceProperties,defaultProperties);
  }





}
