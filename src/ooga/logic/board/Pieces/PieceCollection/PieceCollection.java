package ooga.logic.board.Pieces.PieceCollection;

import java.util.List;
import ooga.logic.board.Pieces.PieceBundle.Piece;

abstract public class PieceCollection {
  private List<String> myPieceList;

  public abstract List<String> getPossiblePieces();
}
