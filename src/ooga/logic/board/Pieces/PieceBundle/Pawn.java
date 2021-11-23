package ooga.logic.board.Pieces.PieceBundle;

import ooga.logic.board.Pieces.PieceCollection.DefaultPromotionPieces;
import ooga.logic.board.Pieces.SpotCollection.FlexibleCoordinateCollection.PromotionSpots;
import ooga.logic.board.coordinate.GameCoordinate;

/**
 * Implement a Pawn that can do the following
 * Promote on the 8th rank
 * Move forward once OR twice if on 2nd rank
 * Move forward once if else
 * can capture top right and top left immediate
 */
public class Pawn extends Piece {
  private static final String PIECE_TO_STRING = "Pawn";

  public Pawn(int xPosition, int yPosition, int team){
    super(PIECE_TO_STRING, team, new GameCoordinate(xPosition,yPosition));
  }

}
