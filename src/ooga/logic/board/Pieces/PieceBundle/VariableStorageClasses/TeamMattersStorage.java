package ooga.logic.board.Pieces.PieceBundle.VariableStorageClasses;

import java.util.Map;
import java.util.ResourceBundle;

public class TeamMattersStorage extends BooleanStorage {
  private static final String MY_STRING = "teamMatters";

  public TeamMattersStorage(Map<String, String> attributeMap, ResourceBundle pieceProperties, ResourceBundle defaultProperties) {
    super(MY_STRING, attributeMap,pieceProperties,defaultProperties);
  }



}
