package ooga.logic.board.Pieces.PieceCollection;

import java.util.ArrayList;
import java.util.List;

public class DefaultPromotionPieces extends PieceCollection{

  private static final String BISHOP = "Bishop";
  private static final String KNIGHT = "Knight";
  private static final String ROOK = "Rook";
  private static final String QUEEN = "Queen";

  public List<String> getPossiblePieces() {
    List<String> myPieces = new ArrayList<>();
    myPieces.add(BISHOP);
    myPieces.add(KNIGHT);
    myPieces.add(ROOK);
    myPieces.add(QUEEN);
    return myPieces;
  }
}

