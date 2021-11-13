package ooga.logic.board;

public class DefaultBoard implements Board{

  @Override
  public int[][] getFullBoard() {
    return new int[8][8];
  }
}
