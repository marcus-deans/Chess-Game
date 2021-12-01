package ooga.logic.board.Pieces.Interfaces;

import java.util.List;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;
import ooga.logic.board.coordinate.Coordinate;

public interface CaptureLogic {

//  public void captures(Coordinate captureCoordinate);

  public SpotCollection getPossibleCaptures();

  public boolean canCapture(Coordinate captureCoordinate);

}
