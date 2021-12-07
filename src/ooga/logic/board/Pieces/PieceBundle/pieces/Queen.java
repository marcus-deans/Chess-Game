package ooga.logic.board.Pieces.PieceBundle.pieces;

import java.util.HashMap;
import java.util.Map;
import ooga.logic.board.coordinate.GameCoordinate;

/**
 * Generic class to hold a default yet flexible queen; default is a piece that moves
 * and captures horizontally and vertically, can't jump, cannibalize, or be checked or anything.
 * @author Amr Tagel-Din
 */
public class Queen extends Piece {
  private static final String PIECE_TO_STRING = "Queen";

  public Queen(int xPosition, int yPosition, int team){
    this(xPosition,yPosition,team,new HashMap<>());
  }

  public Queen(int xPosition, int yPosition, int team, Map<String,String> mapAttributes){
    super(PIECE_TO_STRING, team, new GameCoordinate(xPosition,yPosition), mapAttributes);
  }




}
