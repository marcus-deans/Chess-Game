package ooga.logic.board.Pieces.PieceBundle.pieces;

import java.util.HashMap;
import java.util.Map;
import ooga.logic.board.coordinate.GameCoordinate;

public class Queen extends Piece {
  private static final String PIECE_TO_STRING = "Queen";

  public Queen(int xPosition, int yPosition, int team){
    this(xPosition,yPosition,team,new HashMap<>());
  }

  public Queen(int xPosition, int yPosition, int team, Map<String,String> mapAttributes){
    super(PIECE_TO_STRING, team, new GameCoordinate(xPosition,yPosition), mapAttributes);
  }




}
