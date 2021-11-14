package ooga.logic.board.Pieces.Interfaces;

import java.util.List;
import ooga.logic.board.Coordinate;
import ooga.logic.board.CoordinateUseCase;
import ooga.logic.board.GameCoordinate;

public interface CaptureLogic {

//  public void captures(Coordinate captureCoordinate);

  public List<Coordinate> getPossibleCaptures();

  public boolean canCapture(Coordinate captureCoordinate);

}
