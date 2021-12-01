package ooga.logic.board.Pieces.Interfaces;

import java.util.List;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;
import ooga.logic.board.coordinate.Coordinate;

public interface CaptureLogic {

  public SpotCollection getPossibleCaptures();

  public boolean canCapture(Coordinate captureCoordinate);

}
