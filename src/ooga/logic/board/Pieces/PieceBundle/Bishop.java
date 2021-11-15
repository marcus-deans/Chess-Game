package ooga.logic.board.Pieces.PieceBundle;

import ooga.logic.board.Pieces.SpotCollection.BishopMovement;
import ooga.logic.board.coordinate.GameCoordinate;

public class Bishop extends Piece {
  public Bishop(int xPosition, int yPosition){
    setMyCoordinate(new GameCoordinate(xPosition,yPosition));
    setMyMovement(new BishopMovement());
    setMyCapture(new BishopMovement());
  }


}
