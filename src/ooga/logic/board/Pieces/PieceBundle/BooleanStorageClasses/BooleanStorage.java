package ooga.logic.board.Pieces.PieceBundle.BooleanStorageClasses;

import java.util.Map;
import java.util.ResourceBundle;

abstract public class BooleanStorage implements BooleanStorageInterface {
  private Boolean myVar;
  private String MY_STRING;
  private ResourceBundle pieceProperties;
  private ResourceBundle defaultProperties;

  public BooleanStorage(String myString, Map<String, String> attributeMap, ResourceBundle pieceProperties, ResourceBundle defaultProperties) {
    MY_STRING = myString;
    this.pieceProperties = pieceProperties;
    this.defaultProperties = defaultProperties;
    calculateVariable(attributeMap);
  }

  @Override
  public void update(Map<String,String> myMap){
    calculateVariable(myMap);
  }

  private void calculateVariable(Map<String, String> attributeMap){
    try{
      setVar(Boolean.parseBoolean(mapOrProperty(attributeMap)));
    }
    catch (Exception e){
      setVar(Boolean.parseBoolean(defaultProperties.getString(MY_STRING)));
    }
  }

  private String mapOrProperty(Map<String, String> attributeMap){
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
