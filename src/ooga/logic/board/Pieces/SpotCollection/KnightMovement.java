package ooga.logic.board.Pieces.SpotCollection;

import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

public class KnightMovement extends SpotCollection {
  private static final String PIECE_AS_STRING = "knight";

  @Override
  public List<Coordinate> getPossibleSpots(Coordinate myCoordinate) {
    String[] myDirections = pieceProperties.getString(PIECE_AS_STRING).split(",");
    int[] signs = stringToIntArr(pieceProperties.getString("signs"));
    return doubleSymmetricOver(signs,myCoordinate,myDirections);
  }



}
