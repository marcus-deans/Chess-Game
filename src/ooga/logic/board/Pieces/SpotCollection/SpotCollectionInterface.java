package ooga.logic.board.Pieces.SpotCollection;

import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

/**
 * A 'SpecificSpotCollection' interface is only responsible for returning a collection of specific
 * spots, therefore the only method should be returning those specific spots
 * @author Amr Tagel-Din
 */
public interface SpotCollectionInterface {

  /**
   * get Possible spots of a given coordinate and a given set of rules to follow
   * @param coordinate The coordinate that we are starting at
   * @return the List of valid coordinates based off of the rules we pass into the specific
   * Coordinate calculator
   */
  List<List<Coordinate>> getPossibleSpots(Coordinate coordinate);

}
