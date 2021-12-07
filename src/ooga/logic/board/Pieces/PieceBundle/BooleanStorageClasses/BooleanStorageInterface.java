package ooga.logic.board.Pieces.PieceBundle.BooleanStorageClasses;

import java.util.Map;

/**
 * Using a map that stores keys and values, this interface allows you to calculate and
 * store specific values
 * @author Amr Tagel-Din
 */
public interface BooleanStorageInterface {

  /**
   * Return the boolean we calculate
   * @return the boolean value of a given variable wanted
   */
  boolean getValue();

  /**
   * Recalculate the variable with the addition of the map to potentially change the variable
   * @param myMap Map of variables that may or may not affect what the final variable is
   */
  void update(Map<String,String> myMap);

}
