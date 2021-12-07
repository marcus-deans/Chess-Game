package ooga.logic.board.Pieces.PieceBundle.SpotCollectionStorageClasses;

import java.util.Map;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;

/**
 * Interface that allows you to store and alter a SpotCollection
 * @author Amr Tagel-Din
 */
public interface SpotCollectionStorageInterface {

  /**
   * @return the spotCollection that has been calculated by this class
   */
  SpotCollection getSpotCollection();

  /**
   * Change the map classifying the variables and update
   * @param myMap Map of relevant keys and what we want them to be set to
   */
  void update(Map<String,String> myMap);

}
