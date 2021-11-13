package ooga.logic.board.Pieces;


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
public class King extends Piece {

  public King(){
    this(4,0);
  }
  public King(int xPosition, int yPosition){
    setMyCoordinate(new CoordinateUseCase(xPosition,yPosition));
    updateRankAndFile();
  }

  @Override
  public List<Coordinate> getPossibleCaptures() {
    return getPossibleMoves();
  }

  @Override
  public List<Coordinate> getPossibleMoves() {
    int[] myXInts = new int[]{-1,0,1};
    int[] myYInts = new int[]{-1,0,1};
    return availableSquares(myXInts, myYInts);
  }

  @Override
  public void setState() {

  }
}
