package ooga.logic.board.Pieces.SpotCollection;

import java.util.List;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;

public class PawnCapture extends SpotCollection {
  private static final String PIECE_AS_STRING = "pawnCapture";

  public PawnCapture(){
    super();
  }

  @Override
  public List<Coordinate> getPossibleSpots(Coordinate myCoordinate) {
    List<Coordinate> myList = OneTimePossibleSpots(PIECE_AS_STRING, myCoordinate);

    return OneTimePossibleSpots(PIECE_AS_STRING, myCoordinate);
  }
}
