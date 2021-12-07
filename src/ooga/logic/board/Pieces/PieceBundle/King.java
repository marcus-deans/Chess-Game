package ooga.logic.board.Pieces.PieceBundle;
import java.util.HashMap;
import java.util.Map;
import ooga.logic.board.coordinate.GameCoordinate;

/**
 * Implement a King that can move and eat once in every direction;
 * CAN be checked/ killed as the default win condition
 */
public class King extends Piece {
  private static final String PIECE_TO_STRING = "King";

  /**
   * use the same information plus an empty rulesMap if this wasn't passed in
   * @param xPosition xCoordinate of piece
   * @param yPosition yCoordinate of piece
   * @param team team of piece
   */
  public King(int xPosition, int yPosition, int team){
    this(xPosition,yPosition,team,new HashMap<>());
  }

  /**
   * pass the King back to prep its values
   * @param xPosition xCoordinate of piece
   * @param yPosition yCoordinate of piece
   * @param team team of piece
   * @param mapAttributes the map combining keys and attributes to their values that aren't default
   */
  public King(int xPosition, int yPosition, int team, Map<String,String> mapAttributes){
    super(PIECE_TO_STRING, team, new GameCoordinate(xPosition,yPosition), mapAttributes);
  }
}
