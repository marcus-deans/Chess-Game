package ooga.logic.board.Pieces;

import java.util.List;
import ooga.logic.board.Coordinate;

public interface PromoteLogic {
  public List<Coordinate> promotionSquares();

  public List<PieceLogic> possiblePromotionPieces();

}
