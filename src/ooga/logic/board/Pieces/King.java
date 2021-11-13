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
public class King extends Piece implements MoveLogic, CaptureLogic{

  public King(){
    this(4,0);
  }
  public King(int xPosition, int yPosition){
    setMyCoordinate(new CoordinateUseCase(xPosition,yPosition));
    updateRankAndFile();
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

  @Override
  public List<Coordinate> getPossibleCaptures() {
    return getPossibleMoves();
  }

  private boolean isValidSquare(Coordinate captureCoordinate) {
    // TODO: IMPLEMENT EDGE POLICIES
    return true;
  }

  @Override
  public List<Coordinate> getPossibleMoves() {
    List<Coordinate> myPossibleMoves = new ArrayList<>();
    int[] myXInts = new int[]{-1,0,1};
    int[] myYInts = new int[]{-1,0,1};
    CoordinateUseCase moveCoordinate = new CoordinateUseCase(0, 0);

    for (int xShift : myXInts){
      for (int yShift: myYInts){
        if (!(xShift == 0 && yShift ==0)){
          moveCoordinate.setCoordinate(getMyXCoordinate() + xShift, getMyYCoordinate() + yShift);
          if (isValidSquare(moveCoordinate)){
            myPossibleMoves.add(moveCoordinate);
          }
        }
      }
    }

    return myPossibleMoves;
  }

  @Override
  public void updatePosition(Coordinate passedCoordinate) {
    setMyCoordinate(passedCoordinate);
  }


  @Override
  public void setCoordinate(Coordinate passedCoordinate) {
    setMyCoordinate(passedCoordinate);
  }

  @Override
  public void setState() {

  }

  @Override
  public boolean canCapture(Coordinate captureCoordinate) {
    return getPossibleCaptures().contains(captureCoordinate);
  }

}
