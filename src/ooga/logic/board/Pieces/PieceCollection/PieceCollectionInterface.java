package ooga.logic.board.Pieces.PieceCollection;

import java.util.List;

/**
 * Interface responsible for dealing with collections of pieces
 */
public interface PieceCollectionInterface {

  /**
   * @return the list of piece names you can promote to
   */
  List<String> getPossiblePieces();
}
