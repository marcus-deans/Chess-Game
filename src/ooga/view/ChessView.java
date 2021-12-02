package ooga.view;

import ooga.logic.board.spot.Spot;
import ooga.logic.game.Game;

/**
 * Interface to interact with the view of the program, serves as API
 */
public interface ChessView {

  /**
   * Change the language of the text in the program
   */
  public void changeLanguage();

  /**
   * Change the apperance of the view (i.e. light, dark, etc.)
   */
  public void changeAppearance();

  /**
   *  Update the history of past moves
   */
  public void updateHistory();

  /**
   * Update the graveyard of dead pieces
   */
  public void updateGraveyard();

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
   * Load a game that existed previously
   * @return the prior Game
   */
  public Game loadGame();

  /**
   * Save the current game state to return later
   */
  public void saveGame();

  /**
   * Method to reset the game and begin afresh
   */
  public void resetGame();

  /**
   * Redo a move that was previously undone
   */
  public void redoMove();

  /**
   * Undo the previous move
   */
  public void undoMove();
}
