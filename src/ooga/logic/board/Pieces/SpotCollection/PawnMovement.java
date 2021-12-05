package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.List;

import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;

public class PawnMovement extends SpotCollection {
  private static final String PIECE_AS_STRING = "pawnMovement";

  public PawnMovement(){
    super();
  }

  @Override
  public List<List<Coordinate>> getPossibleSpots(Coordinate myCoordinate) {
    List<List<Coordinate>> mySpots = OneTimePossibleSpots(PIECE_AS_STRING, myCoordinate);
    if (myCoordinate.getY_pos() == 1){
      Coordinate myCoord = new GameCoordinate(myCoordinate.getX_pos(),myCoordinate.getY_pos() + 2);
      mySpots.get(0).add(myCoord);
    }

    return mySpots;
  }
}
