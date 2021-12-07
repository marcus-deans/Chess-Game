package ooga.logic.board.Pieces.PieceBundle.BooleanStorageClasses;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * Generic structure to calculate and store a boolean variable
 * @author Amr Tagel-Din
 */
abstract public class BooleanStorage implements BooleanStorageInterface {
  private Boolean myVar;
  private String MY_STRING;
  private ResourceBundle pieceProperties;
  private ResourceBundle defaultProperties;

  /**
   * Initialize values and calculate the variables
   * @param myString the data String to parse the map
   * @param attributeMap the map of keys and their values relative to the piece;
   * @param pieceProperties properties of the specific piece we're looking at
   * @param defaultProperties default properties if we can't find the property in the map or the resourceBundle
   */
  public BooleanStorage(String myString, Map<String, String> attributeMap, ResourceBundle pieceProperties, ResourceBundle defaultProperties) {
    MY_STRING = myString;
    this.pieceProperties = pieceProperties;
    this.defaultProperties = defaultProperties;
    calculateVariable(attributeMap);
  }

  /**
   * recalculate the variable in case the data map is
   * @param myMap Map of variables that may or may not affect what the final variable is
   */
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

  /**
   * @return whatever boolean variable was calculated by calculateVariable
   */
  @Override
  public boolean getValue(){
    return myVar;
  }

}
