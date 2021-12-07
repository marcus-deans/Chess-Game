package ooga.logic.board.Pieces.SpotCollection;

import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

public class KnightMovement extends SpotCollection {
  private static final String PIECE_AS_STRING = "knight";
  private static final String SIGNS_AS_STRING = "signs";

  public KnightMovement(int width){
    super(width);
  }

  @Override
  public List<List<Coordinate>> getPossibleSpots(Coordinate myCoordinate) {
    String[] myDirections = getPieceProperties().getString(PIECE_AS_STRING).split(",");
    int[] signs = stringToIntArr(getPieceProperties().getString(SIGNS_AS_STRING));
    return doubleSymmetricOver(signs,myCoordinate,myDirections);
  }



}
