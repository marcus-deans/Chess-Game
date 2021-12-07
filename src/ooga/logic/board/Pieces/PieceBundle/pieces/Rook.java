package ooga.logic.board.Pieces.PieceBundle.pieces;

import java.util.HashMap;
import java.util.Map;
import ooga.logic.board.coordinate.GameCoordinate;

/**
 * Generic class to hold a default yet flexible rook; default is a piece that moves
 * and captures horizontally, can't jump, cannibalize, or be checked or anything.
 * @author Amr Tagel-Din
 */
public class Rook extends Piece {
  private static final String PIECE_TO_STRING = "Rook";

  public Rook(int xPosition, int yPosition, int team){
    this(xPosition,yPosition,team,new HashMap<>());
  }

  public Rook(int xPosition,int yPosition, int team, Map<String,String> mapAttributes){
    super(PIECE_TO_STRING, team, new GameCoordinate(xPosition,yPosition), mapAttributes);
  }

  }
