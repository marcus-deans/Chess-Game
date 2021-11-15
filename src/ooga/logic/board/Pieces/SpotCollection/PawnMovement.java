package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.List;

import ooga.logic.board.coordinate.Coordinate;

public class PawnMovement extends SpotCollection {

  @Override
  public List<Coordinate> getPossibleSpots(Coordinate myCoordinate) {
    List<Coordinate> myPossibleMoves = new ArrayList<>();
    myPossibleMoves.add(Forward(myCoordinate, 1));
    myPossibleMoves.add(Forward(myCoordinate,2));

    return myPossibleMoves;
  }
}
