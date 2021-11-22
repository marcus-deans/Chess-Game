package ooga.logic.board.Pieces.PieceBundle;

import ooga.logic.board.Pieces.SpotCollection.QueenMovement;
import ooga.logic.board.Pieces.SpotCollection.RookMovement;
import ooga.logic.board.coordinate.GameCoordinate;

public class Queen extends Piece {
  private static final String PIECE_TO_STRING = "Queen";

  public Queen(int xPosition, int yPosition, int team){
    super(PIECE_TO_STRING);

    setTeam(team);
    setCoordinate(new GameCoordinate(xPosition,yPosition));
    setMyMovement(new QueenMovement());
    setMyCapture(new QueenMovement());
  }


}
