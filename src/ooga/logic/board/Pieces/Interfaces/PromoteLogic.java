package ooga.logic.board.Pieces.Interfaces;

import java.util.List;
import ooga.logic.board.Pieces.PieceCollection.PieceCollection;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;
import ooga.logic.board.coordinate.Coordinate;

public interface PromoteLogic {
  public SpotCollection promotionSquares();

  public PieceCollection possiblePromotionPieces();

}
