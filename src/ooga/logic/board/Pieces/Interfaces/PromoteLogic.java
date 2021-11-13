package ooga.logic.board.Pieces.Interfaces;

import java.util.List;
import ooga.logic.board.Coordinate;
import ooga.logic.board.Pieces.Interfaces.PieceLogic;

public interface PromoteLogic {
  public List<Coordinate> promotionSquares();

  public List<PieceLogic> possiblePromotionPieces();

}
