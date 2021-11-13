package ooga.logic.board.Pieces;


import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.Coordinate;
import ooga.logic.board.CoordinateUseCase;
import ooga.logic.board.Pieces.Interfaces.PieceLogic;

/**
 * Implement a Pawn that can do the following
 * Promote on the 8th rank
 * Move forward once OR twice if on 2nd rank
 * Move forward once if else
 * can capture top right and top left immediate
 */
public class Pawn extends Piece {

  public Pawn(){
    this(0,1);
  }
  public Pawn(int xPosition, int yPosition){
    setMyCoordinate(new CoordinateUseCase(xPosition,yPosition));
    updateRankAndFile();
  }


  @Override
  public List<Coordinate> getPossibleCaptures() {
    int[] addXAmount = new int[]{-1,1};
    int[] addYAmount = new int[]{1};
    return availableSquares(addXAmount,addYAmount);
  }


  @Override
  public List<Coordinate> getPossibleMoves() {
    List<Coordinate> myPossibleMoves = new ArrayList<>();
    CoordinateUseCase moveCoordinate = new CoordinateUseCase(getMyXCoordinate(), getMyYCoordinate() + 1);
    myPossibleMoves.add(moveCoordinate);
    if (getMyRank() == 1){
      moveCoordinate.setCoordinate(getMyXCoordinate(), getMyYCoordinate() + 2);
      myPossibleMoves.add(moveCoordinate);
    }
    return myPossibleMoves;
  }

  @Override
  public void setState() {

  }

  @Override
  public List<Coordinate> promotionSquares() {
    List<Integer> xOfSquares = new ArrayList<>();
    for (int i = 0; i < 8; i++){
      xOfSquares.add(i);
    }
    List<Integer> yOfSquares = new ArrayList<>();
      yOfSquares.add(7);

    List<Coordinate> myCoordinateList = new ArrayList<>();
    Coordinate newCapture;

    for (int xPos : xOfSquares){
      for (int yPos: yOfSquares){
        newCapture = new CoordinateUseCase(xPos,yPos);
        myCoordinateList.add(newCapture);
      }
    }
    return myCoordinateList;
  }

  @Override
  public List<PieceLogic> possiblePromotionPieces(){
  List<PieceLogic> boops = new ArrayList<>();
  // define: Bishop rook knight queen for now
  return boops;
  }


}
