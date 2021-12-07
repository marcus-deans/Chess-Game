package ooga.logic.board.Pieces.PieceCollection;

import java.util.List;

/**
 * DefaultPromotionPieces
 */
public class DefaultPromotionPieces extends PieceCollection{

  private static final String BISHOP = "Bishop";
  private static final String KNIGHT = "Knight";
  private static final String ROOK = "Rook";
  private static final String QUEEN = "Queen";

  /**
   * Call upon the super to initialize the pieceList
   */
  public DefaultPromotionPieces(){
    super();
  }

  /**
   * @return the possible pieces to promote to
   */
  @Override
  public List<String> getPossiblePieces() {
    addToPieceCollection(BISHOP);
    addToPieceCollection(KNIGHT);
    addToPieceCollection(ROOK);
    addToPieceCollection(QUEEN);
    return getMyPieceList();
  }
}

