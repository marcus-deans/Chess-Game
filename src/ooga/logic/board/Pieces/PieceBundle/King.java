package ooga.logic.board.Pieces.PieceBundle;


import ooga.logic.board.Pieces.SpotCollection.KingMovement;
import ooga.logic.board.coordinate.GameCoordinate;

/**
 * Implement a Pawn that can do the following
 * Promote on the 8th rank
 * Move forward once OR twice if on 2nd rank
 * Move forward once if else
 * can capture top right and top left immediate
 */
public class King extends Piece {

  public King(int team, int xPosition, int yPosition){
    setMyCoordinate(new GameCoordinate(xPosition,yPosition));
    setMyMovement(new KingMovement());
    setMyCapture(new KingMovement());
//    setMyPromotionSpots(new finalRankPromotionSpots());
  }

}
