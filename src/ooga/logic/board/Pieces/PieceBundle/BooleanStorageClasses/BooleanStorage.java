package ooga.logic.board.Pieces.PieceBundle.BooleanStorageClasses;

import java.util.Map;
import java.util.ResourceBundle;

abstract public class BooleanStorage implements BooleanStorageInterface {
  private Boolean myVar;
  private String MY_STRING;

  public BooleanStorage(String myString, Map<String, String> attributeMap, ResourceBundle pieceProperties, ResourceBundle defaultProperties) {
    MY_STRING = myString;
    calculateVariable(attributeMap,pieceProperties,defaultProperties);
  }

  private void calculateVariable(Map<String, String> attributeMap, ResourceBundle pieceProperties, ResourceBundle defaultProperties){
    try{
      setVar(Boolean.parseBoolean(mapOrProperty(attributeMap,pieceProperties)));
    }
    catch (Exception e){
      setVar(Boolean.parseBoolean(defaultProperties.getString(MY_STRING)));
    }
  }

  private String mapOrProperty(Map<String, String> attributeMap, ResourceBundle pieceProperties){
    return (attributeMap.containsKey(MY_STRING)) ? attributeMap.get(MY_STRING) : pieceProperties.getString(MY_STRING);

  }

  private void setVar(boolean myVariable) {
    myVar = myVariable;
  }

  @Override
  public boolean getValue(){
    return myVar;
  }

}
