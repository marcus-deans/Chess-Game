package ooga.logic.board.Pieces.SpotCollection;

import java.util.List;

import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;

/**
 * Class that stores the possible coordinates a reverse pawn could reach from a given coordinate
 */
public class ReversePawnMovement extends SpotCollection {
  private static final String PIECE_AS_STRING = "reversePawnMovement";

  public ReversePawnMovement(int width){
    super(width);
  }

  @Override
  public List<List<Coordinate>> getPossibleSpots(Coordinate myCoordinate) {
    List<List<Coordinate>> mySpots = OneTimePossibleSpots(PIECE_AS_STRING, myCoordinate);

    if (myCoordinate.getY_pos() == 6){
      Coordinate myCoord = new GameCoordinate(myCoordinate.getX_pos(),myCoordinate.getY_pos() - 2);
      mySpots.get(0).add(myCoord);
    }

    return mySpots;
  }
}
