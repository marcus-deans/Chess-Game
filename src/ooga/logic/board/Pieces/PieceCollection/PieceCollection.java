package ooga.logic.board.Pieces.PieceCollection;

import java.util.List;

abstract public class PieceCollection {
  private List<String> myPieceList;

  public abstract List<String> getPossiblePieces();
}
