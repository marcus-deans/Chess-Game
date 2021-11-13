package ooga.logic.board.Pieces;

import java.util.List;
import ooga.logic.board.Coordinate;

public interface Promotes {
  public void promote();

  public List<Coordinate> promotionSquares();

  public List<Piece> possiblePromotionPieces();

}
