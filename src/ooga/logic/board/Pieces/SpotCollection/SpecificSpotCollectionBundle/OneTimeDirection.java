package ooga.logic.board.Pieces.SpotCollection.SpecificSpotCollectionBundle;

import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

/**
 * This class is used to receive Specific spot away from the individual state
 * this is for examples like Pawns, and Knights, which follow a specific line/ movement that does not
 * extend infinitely
 * @author Amr Tagel-Din
 */
public class OneTimeDirection extends SpecificSpotCollection {

  /**
   * return the specific spot that we are pointing to, or an empty list if this spot is invalid
   * @param coordinate The coordinate that we are starting at
   * @param xChange the xChange is the x distance away from the coordinate we will check
   * @param yChange the yChange is the y distance away from the coordinate we will check
   * @return an empty list if the coordinate is invalid, or a list with the single spot if it is
   * valid
   */
  @Override
  public List<Coordinate> getPossibleSpots(Coordinate coordinate, int xChange, int yChange) {
    return Diagonal(coordinate, xChange, yChange);
  }
}