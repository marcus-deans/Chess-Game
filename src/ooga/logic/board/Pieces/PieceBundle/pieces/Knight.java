package ooga.logic.board.Pieces.PieceBundle.pieces;

import java.util.HashMap;
import java.util.Map;
import ooga.logic.board.coordinate.GameCoordinate;

public class Knight extends Piece {
  private static final String PIECE_TO_STRING = "Knight";
  public Knight(int xPosition, int yPosition, int team){
    this(xPosition,yPosition,team,new HashMap<>());
  }

  public Knight(int xPosition, int yPosition, int team, Map<String,String> mapAttributes){
    super(PIECE_TO_STRING, team, new GameCoordinate(xPosition,yPosition), mapAttributes);
  }



}
