package ooga.logic.board.Pieces.PieceCollection;

import java.util.ArrayList;
import java.util.List;

public class DefaultPromotionPieces extends PieceCollection{

  public List<String> getPossiblePieces() {
    List<String> myPieces = new ArrayList<>();
    myPieces.add("Bishop");
    myPieces.add("Knight");
    myPieces.add("Rook");
    myPieces.add("Queen");
    return myPieces;
  }
}

