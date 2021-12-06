package ooga.logic.board.Pieces.PieceBundle.BooleanStorageClasses;

import java.util.Map;
import java.util.ResourceBundle;

abstract public class BooleanStorage {
  private Boolean myVar;
  private String MY_STRING;

  public BooleanStorage(String myString, Map<String, String> attributeMap, ResourceBundle pieceProperties, ResourceBundle defaultProperties) {
    MY_STRING = myString;
    calculateVariable(attributeMap,pieceProperties,defaultProperties);
  }

  private void calculateVariable(Map<String, String> attributeMap, ResourceBundle pieceProperties, ResourceBundle defaultProperties){
    try{

      if (attributeMap.containsKey(MY_STRING)){
        setVar(Boolean.parseBoolean(attributeMap.get(MY_STRING)));
      }
      else{
        setVar(Boolean.parseBoolean(pieceProperties.getString(MY_STRING)));
      }
    }
    catch (Exception e){
      setVar(Boolean.parseBoolean(defaultProperties.getString(MY_STRING)));
    }
  }

  private void setVar(boolean myVariable) {
    myVar = myVariable;
  }

  public boolean getValue(){
    return myVar;
  }

}
