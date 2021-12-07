package ooga.logic.board.Pieces.PieceBundle.pieces;
import java.util.HashMap;
import java.util.Map;
import ooga.logic.board.coordinate.GameCoordinate;

/**
 * Implement a Pawn that can do the following
 * Promote on the 8th rank
 * Move forward once OR twice if on 2nd rank
 * Move forward once if else
 * can capture top right and top left immediate
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
