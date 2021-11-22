package ooga.logic.board.Pieces.PieceBundle;

import ooga.logic.board.coordinate.GameCoordinate;

public class Rook extends Piece {
  private static final String PIECE_TO_STRING = "Rook";

  public Rook(int xPosition, int yPosition, int team){
    super(PIECE_TO_STRING, team, new GameCoordinate(xPosition,yPosition));
  }
}
