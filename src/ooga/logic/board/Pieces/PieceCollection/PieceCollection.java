package ooga.logic.board.Pieces.PieceCollection;

import java.util.List;
import ooga.logic.board.Pieces.Piece;

abstract public class PieceCollection {
  private List<Piece> myPieceList;

  public abstract List<Piece> getPossiblePieces();
}
