package ooga.logic.board.Pieces;


import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.Coordinate;
import ooga.logic.board.CoordinateUseCase;
import ooga.logic.board.Pieces.Interfaces.PieceLogic;
import ooga.logic.board.Pieces.Movements.Movement;
import ooga.logic.board.Pieces.Movements.PawnCapture;
import ooga.logic.board.Pieces.Movements.PawnMovement;

/**
 * Implement a Pawn that can do the following
 * Promote on the 8th rank
 * Move forward once OR twice if on 2nd rank
 * Move forward once if else
 * can capture top right and top left immediate
 */
public class Pawn extends Piece {
  private Movement myMovement;
  private Movement myCapture;

  public Pawn(){
    this(0,1);
  }
  public Pawn(int xPosition, int yPosition){
    setMyCoordinate(new CoordinateUseCase(xPosition,yPosition));
    updateRankAndFile();
    myMovement = new PawnMovement();
    myCapture = new PawnCapture();
  }


  @Override
  public List<Coordinate> getPossibleCaptures() {
    return myCapture.getPossibleMoves(getCoordinate());
  }


  @Override
  public List<Coordinate> getPossibleMoves() {
    return myMovement.getPossibleMoves(getCoordinate());
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
