package ooga.logic.board.Pieces;


import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.Coordinate;
import ooga.logic.board.CoordinateUseCase;

/**
 * Implement a Pawn that can do the following
 * Promote on the 8th rank
 * Move forward once OR twice if on 2nd rank
 * Move forward once if else
 * can capture top right and top left immediate
 */
public class Pawn implements Piece,Moves,Captures,Promotes {


  private int state=1;//Represents a pawn
  private List<Coordinate> possibleMoves;
  private Coordinate myCoordinate;


  public Pawn(){
    possibleMoves = new ArrayList<>();
    myCoordinate = new CoordinateUseCase(1,1);

  }

  @Override
  public void captures(Coordinate captureCoordinate) {
    if (canCapture(captureCoordinate)){
      myCoordinate.setCoordinate(captureCoordinate);
      // TODO: remove the piece thats on this square in the board
    }
  }

  @Override
  public List<Coordinate> getPossibleCaptures() {
    List<Coordinate> myCoordinateList = new ArrayList<>();
    Coordinate newCapture;
    int[] addXAmount = new int[]{-1,1};
    int[] addYAmount = new int[]{1};

    for (int xAmt : addXAmount){
      for (int yAmt: addYAmount){
        newCapture = new CoordinateUseCase(myCoordinate.getX_pos() + xAmt, myCoordinate.getY_pos() + yAmt);
        if (isValidSquare(newCapture)){
          myCoordinateList.add(newCapture);
        }
      }
    }
    return myCoordinateList;
  }

  private boolean isValidSquare(Coordinate captureCoordinate) {
    // for now it doesn't matter
   return true;
  }


  @Override
  public void moves() {
    possibleChanges.add(new CoordinateUseCase(0,1));
  }

  @Override
  public void updatePossibleMoves() {
    for (Coordinate c:possibleChanges)
    {
      Coordinate newPos=new CoordinateUseCase(currentLoc.getX_pos()+c.getX_pos(),currentLoc.getY_pos()+c.getY_pos());
      possibleMoves.add(newPos);
    }
  }

  @Override
  public List<Coordinate> getPossibleMoves() {
    return null;
  }

  @Override
  public void updatePosition() {

  }

  @Override
  public void setCoordinate() {

  }

  @Override
  public void setState() {

  }

  @Override
  public Coordinate getCoordinate() {
    return null;
  }

  @Override
  public void remove() {

  }

  @Override
  public void promote() {

  }

  @Override
  public List<Coordinate> promotionSquares() {
    return null;
  }

  @Override
  public List<Piece> possiblePromotionPieces(){
  List<Piece> boops = new ArrayList<>();
  return boops;
  }

  @Override
  public boolean canCapture(Coordinate captureCoordinate) {
    return getPossibleCaptures().contains(captureCoordinate);
  }

}
