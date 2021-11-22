package ooga.logic.board.Pieces.PieceBundle;

import ooga.logic.board.Pieces.SpotCollection.KnightMovement;
import ooga.logic.board.coordinate.GameCoordinate;

public class Knight extends Piece {
  private static final String PIECE_TO_STRING = "Knight";

  public Knight(int xPosition, int yPosition, int team){
    setTeam(team);
    setCoordinate(new GameCoordinate(xPosition,yPosition));
    setMyMovement(new KnightMovement());
    setMyCapture(new KnightMovement());
  }

}
