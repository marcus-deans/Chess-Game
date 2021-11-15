package ooga.logic.board.Pieces.PieceBundle;

import ooga.logic.board.Pieces.SpotCollection.QueenMovement;
import ooga.logic.board.coordinate.GameCoordinate;

public class Knight extends Piece {
  public Knight(int xPosition, int yPosition){
    setMyCoordinate(new GameCoordinate(xPosition,yPosition));
    setMyMovement(new KnightMovement());
    setMyCapture(new KnightMovement());
  }


}
