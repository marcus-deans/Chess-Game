package ooga.logic.board.Pieces.SpotCollection.SpecificSpotCollectionBundle;

import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

/**
 * This class is used to receive a continuous line of specific spots until we hit an invalid state;
 * this is for examples like bishops, rooks, queens, etc which can keep moving until they hit a
 * wall or a piece
 * @author Amr Tagel-Din
 */
public class ContinuousLine extends SpecificSpotCollection {

  /**
   * @param width max of width or height
   */
  public ContinuousLine(int width){
    setMyWidth(width);
  }
  /**
   * Starting at the coordinate, follow a line with the slope of 'yDirection/xDirection' and return
   * the valid squares in this line
   * @param coordinate The coordinate that we are starting at
   * @param xDirection the xDirection gives us the x-axis direction for us to incrementally follow
   * @param yDirection the yDirection gives us the y-axis direction for us to incrementally follow
   * @return the List of valid coordinates that follow the pattern of being 'xDirection' away from
   * the coordinate in the x axis, and 'ydirection' away in the y axis
   */
  @Override
  public List<Coordinate> getPossibleSpots(Coordinate coordinate, int xDirection,int yDirection) {
    return diagonalSquares(coordinate, xDirection, yDirection);
  }


}