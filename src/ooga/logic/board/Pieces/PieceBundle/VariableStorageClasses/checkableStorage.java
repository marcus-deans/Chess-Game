package ooga.logic.board.Pieces.PieceBundle.VariableStorageClasses;

import java.util.Map;
import java.util.ResourceBundle;

public class checkableStorage extends BooleanStorage {
  private static final String IS_CHECKABLE = "isCheckable";
  private boolean checkable;

  public checkableStorage(Map<String, String> attributeMap, ResourceBundle pieceProperties, ResourceBundle defaultProperties) {
    setCheckableStorage(attributeMap,pieceProperties,defaultProperties);
  }

  private void setCheckableStorage(Map<String, String> attributeMap, ResourceBundle pieceProperties, ResourceBundle defaultProperties) {
    try{
      if (attributeMap.containsKey(IS_CHECKABLE)){
        setCheckable(Boolean.parseBoolean(attributeMap.get(IS_CHECKABLE)));
      }
      else{
        setCheckable(Boolean.parseBoolean(pieceProperties.getString(IS_CHECKABLE)));
      }
    }
    catch (Exception e){
      setCheckable(Boolean.parseBoolean(defaultProperties.getString(IS_CHECKABLE)));
    }
  }

  private void setCheckable(boolean check) {
    checkable = check;
  }

  @Override
  public boolean getValue() {
    return checkable;
  }

}
