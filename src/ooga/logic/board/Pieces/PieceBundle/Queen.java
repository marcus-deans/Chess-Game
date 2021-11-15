package ooga.logic.board.Pieces.PieceBundle;

import ooga.logic.board.Pieces.SpotCollection.QueenMovement;
import ooga.logic.board.Pieces.SpotCollection.RookMovement;
import ooga.logic.board.coordinate.GameCoordinate;

public class Queen extends Piece {
  public Queen(int xPosition, int yPosition){
    setMyCoordinate(new GameCoordinate(xPosition,yPosition));
    setMyMovement(new QueenMovement());
    setMyCapture(new QueenMovement());
  }


}
