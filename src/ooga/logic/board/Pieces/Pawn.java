package ooga.logic.board.Pieces;


import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.GameCoordinate;
import ooga.logic.board.Pieces.PieceCollection.DefaultPromotionPieces;
import ooga.logic.board.Pieces.SpotCollection.KingMovement;
import ooga.logic.board.Pieces.SpotCollection.PawnCapture;
import ooga.logic.board.Pieces.SpotCollection.PawnMovement;
import ooga.logic.board.Pieces.SpotCollection.finalRankPromotionSpots;

/**
 * Implement a Pawn that can do the following
 * Promote on the 8th rank
 * Move forward once OR twice if on 2nd rank
 * Move forward once if else
 * can capture top right and top left immediate
 */
public class Pawn extends Piece {
  public Pawn(int team, int xPosition, int yPosition){
    setMyCoordinate(new GameCoordinate(xPosition,yPosition));
    updateRankAndFile();
    setMyMovement(new PawnMovement());
    setMyCapture(new PawnCapture());
    setMyPromotionSpots(new finalRankPromotionSpots());
    setMyPromotionPieces(new DefaultPromotionPieces());
  }


}
