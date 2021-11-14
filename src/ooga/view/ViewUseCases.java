package ooga.view;

public class ViewUseCases implements View{

  /**
   * Change the language of the text in the program
   */
  @Override
  public void changeLanguage() {
    
  }

  /**
   * Change the apperance of the view (i.e. light, dark, etc.)
   */
  @Override
  public void changeAppearance() {

  }

  /**
   * Update the history of past moves
   */
  @Override
  public void updateHistory() {

  }

  /**
   * Update the graveyard of dead pieces
   */
  @Override
  public void updateGraveyard() {

  }

  /**
   * Load a game that existed previously
   *
   * @return the prior Game
   */
  @Override
  public Game loadGame() {
    return null;
  }

  /**
   * Save the current game state to return later
   */
  @Override
  public void saveGame() {

  }

  /**
   * Method to reset the game and begin afresh
   */
  @Override
  public void resetGame() {

  }

  /**
   * Redo a move that was previously undone
   */
  @Override
  public void redoMove() {

  }

  /**
   * Undo the previous move
   */
  @Override
  public void undoMove() {

  }
}
