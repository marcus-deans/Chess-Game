package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.List;

import jdk.jshell.Diag;
import ooga.logic.board.coordinate.Coordinate;

public class PawnMovement extends SpotCollection {

  @Override
  public List<Coordinate> getPossibleSpots(Coordinate myCoordinate) {
    List<Coordinate> myPossibleMoves = new ArrayList<>();
    myPossibleMoves.add(Diagonal(myCoordinate, 0,1));
    myPossibleMoves.add(Diagonal(myCoordinate,0,2));

    return myPossibleMoves;
  }
}
