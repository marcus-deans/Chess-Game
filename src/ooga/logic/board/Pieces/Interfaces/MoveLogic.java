package ooga.logic.board.Pieces.Interfaces;

import java.util.List;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;
import ooga.logic.board.coordinate.Coordinate;

public interface MoveLogic {



  /**
   *
   * @return the list of possible coordinates to move to
   */
  SpotCollection getPossibleMoves();

  /**
   * Changes the coordinate set for the piece
   */
  void setCoordinate(Coordinate passedCoordinate);

  void setCanJump(boolean canJump);


  boolean getCanJump();

}



