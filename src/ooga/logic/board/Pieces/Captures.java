package ooga.logic.board.Pieces;

import java.util.List;
import ooga.logic.board.Coordinate;

public interface Captures {

  public void captures();

  public List<Coordinate> possibleCaptures();

}
