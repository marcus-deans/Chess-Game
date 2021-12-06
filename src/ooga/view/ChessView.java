package ooga.view;

import java.lang.reflect.InvocationTargetException;
import ooga.logic.board.Pieces.PieceBundle.Piece;
import ooga.logic.board.spot.Spot;
import ooga.logic.game.Game;

/**
 * Interface to interact with the view of the program, serves as API
 */
public interface ChessView {

  /**
   *  Add to the history of past moves
   */
  public void addHistory(String historyText);

  /**
   * Remove from the history of past moves
   */
  public void removeHistory();

  /**
   * Update the graveyard of dead pieces
   */
  public void updateGraveyard(Piece deadPiece);

  /**
   * Update a specific cell on the board
   * @param spot the spot that should be updated
   */
  public void updateChessCell(Spot spot);

  /**
   * Highlight a specific cell on the board
   * @param spot the spoit that should be highlighted
   */
  public void highlightChessCell(Spot spot);

  /**
   * Redo a move that was previously undone
   */
  public void redoMove() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

  /**
   * Undo the previous move
   */
  public void undoMove() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
