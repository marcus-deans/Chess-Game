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
   * @param width the max of either the width or height of the board
   */
  public OneTimeDirection(int width){
    setMyWidth(width);
  }


  /**
   * Get the possible coordinates in a given direction
   * @param coordinate The coordinate that we are starting at
   * @param xChange the xChange is the change in the xDirection we will take either once, or repeatedly
   * @param yChange the yChange is the change in the yDirection we will take either once, or repeatedly
   * @return
   */
  @Override
  public List<Coordinate> getPossibleSpots(Coordinate coordinate, int xChange, int yChange) {
    return Diagonal(coordinate, xChange, yChange);
  }
}