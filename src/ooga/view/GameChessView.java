package ooga.view;

import java.lang.reflect.InvocationTargetException;

import javafx.stage.Stage;
import ooga.logic.board.Pieces.PieceBundle.Piece;
import ooga.logic.board.spot.Spot;

/**
 * Interface to interact with the view of the program, serves as API
 * Used extensively by Controller
 *
 * @author marcusdeans
 */
public interface GameChessView {

  /**
   *  Add to the history of past moves
   */
  public void addHistory(String historyText);

  /**
   * Remove from the history of past moves
   */
  public void removeHistory();

  public void start(Stage primaryStage);

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
   * @param spot the spot that should be highlighted
   * @param hexColour
   */
  public void colourChessCell(Spot spot, String hexColour);

  /**
   * Redo a move that was previously undone
   */
  public void redoMove() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

  /**
   * Undo the previous move
   */
  public void undoMove() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

  /**
   * MWhen game is complete, execute win demonstration
   * @param teamNumber the team that won
   */
  public void displayGameComplete(int teamNumber);

  /**
   * Set the description of the current simulation
   * @param boardDescription the text description
   */
  public void setBoardDescription(String boardDescription);
}
