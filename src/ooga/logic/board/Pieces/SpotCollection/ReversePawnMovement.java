package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.List;

import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;

public class ReversePawnMovement extends SpotCollection {
  private static final String PIECE_AS_STRING = "reversePawnMovement";

  public ReversePawnMovement(){
    super();
  }

  @Override
  public List<List<Coordinate>> getPossibleSpots(Coordinate myCoordinate) {
    List<List<Coordinate>> mySpots = OneTimePossibleSpots(PIECE_AS_STRING, myCoordinate);

    if (myCoordinate.getY_pos() == 6){
      Coordinate myCoord = new GameCoordinate(myCoordinate.getX_pos(),myCoordinate.getY_pos() - 2);
      List<Coordinate> myMiniList = new ArrayList<>();
      myMiniList.add(myCoord);
      mySpots.add(myMiniList);
    }

    return mySpots;
  }
}
