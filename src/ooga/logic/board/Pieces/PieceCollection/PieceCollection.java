package ooga.logic.board.Pieces.PieceCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that stores a list of strings representing what you can promote to
 */
abstract public class PieceCollection implements  PieceCollectionInterface{
  private List<String> myPieceList;

  /**
   * Initialize the pieceList as a new arrayList
   */
  public PieceCollection(){
    myPieceList = new ArrayList<>();

  }
  protected void addToPieceCollection(String toAdd){
    myPieceList.add(toAdd);
  }
  protected List<String> getMyPieceList(){
    return myPieceList;
  }

  /**
   * @return the list of possible pieces
   */
  @Override
  public abstract List<String> getPossiblePieces();
}
