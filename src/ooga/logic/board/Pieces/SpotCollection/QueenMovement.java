package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

public class QueenMovement extends SpotCollection {

  public QueenMovement(int width){
    super(width);
  }

  @Override
  public List<List<Coordinate>> getPossibleSpots(Coordinate myCoordinate) {
    List<List<Coordinate>> myCoords = new ArrayList<>();
    myCoords.addAll(new RookMovement(getBoardWidth()).getPossibleSpots(myCoordinate));
    myCoords.addAll(new BishopMovement(getBoardWidth()).getPossibleSpots(myCoordinate));
    return myCoords;
  }
}
