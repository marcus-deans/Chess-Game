package ooga.logic.board.Pieces.PieceBundle.pieces;
import java.util.HashMap;
import java.util.Map;
import ooga.logic.board.coordinate.GameCoordinate;

/**
 * Implement a King that can move and eat once in every direction;
 * CAN be checked/ killed as the default win condition
 */
public class King extends Piece {
  private static final String PIECE_TO_STRING = "King";
  public King(int xPosition, int yPosition, int team){
    this(xPosition,yPosition,team,new HashMap<>());
  }

  public King(int xPosition, int yPosition, int team, Map<String,String> mapAttributes){
    super(PIECE_TO_STRING, team, new GameCoordinate(xPosition,yPosition), mapAttributes);
  }
}
