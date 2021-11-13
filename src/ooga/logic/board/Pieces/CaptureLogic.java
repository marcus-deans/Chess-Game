package ooga.logic.board.Pieces;

import java.util.List;
import ooga.logic.board.Coordinate;

public interface CaptureLogic {

//  public void captures(Coordinate captureCoordinate);

  public List<Coordinate> getPossibleCaptures();

  public boolean canCapture(Coordinate captureCoordinate);

}
