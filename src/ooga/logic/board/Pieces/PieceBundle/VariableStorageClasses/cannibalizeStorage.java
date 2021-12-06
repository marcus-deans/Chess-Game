package ooga.logic.board.Pieces.PieceBundle.VariableStorageClasses;

import java.util.Map;
import java.util.ResourceBundle;

public class cannibalizeStorage extends BooleanStorage {
  private static final String CANNIBALIZE = "canCannibalize";
  private boolean canCannibalize;

  public cannibalizeStorage(Map<String, String> attributeMap, ResourceBundle pieceProperties, ResourceBundle defaultProperties) {
    setCannibalize(attributeMap,pieceProperties,defaultProperties);
  }


  private void setCannibalize(Map<String, String> attributeMap, ResourceBundle pieceProperties, ResourceBundle defaultProperties) {
    try{
      if (attributeMap.containsKey(CANNIBALIZE)){
        setCanCannibalize(Boolean.parseBoolean(attributeMap.get(CANNIBALIZE)));
      }
      else{
        setCanCannibalize(Boolean.parseBoolean(pieceProperties.getString(CANNIBALIZE)));
      }
    }
    catch (Exception e){
      setCanCannibalize(Boolean.parseBoolean(defaultProperties.getString(CANNIBALIZE)));
    }
  }

  private void setCanCannibalize(boolean canCannib) {
    canCannibalize = canCannib;
  }

  @Override
  public boolean getValue() {
    return canCannibalize;
  }

}
