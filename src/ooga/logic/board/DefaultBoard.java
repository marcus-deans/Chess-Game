package ooga.logic.board;

public class DefaultBoard implements Board{

  @Override
  public int[][] getFullBoard() {
    return new int[8][8];
  }

  //  @Override
//  public void captures(Coordinate captureCoordinate) {
//    if (canCapture(captureCoordinate)){
//      myCoordinate.setCoordinate(captureCoordinate);
//      // TODO: remove the piece thats on this square in the board
//    }
//  }

//  @Override
//  public void moves() {
//  }
}
