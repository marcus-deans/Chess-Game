package ooga.logic.board.Pieces.Interfaces;

import java.util.List;
import ooga.logic.board.coordinate.Coordinate;

public interface PromoteLogic {
  public List<Coordinate> promotionSquares();

  public List<String> possiblePromotionPieces();

}
