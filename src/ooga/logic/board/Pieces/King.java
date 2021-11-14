package ooga.logic.board.Pieces;


import java.util.List;
import ooga.logic.board.Coordinate;
import ooga.logic.board.GameCoordinate;
import ooga.logic.board.Pieces.Interfaces.CaptureLogic;
import ooga.logic.board.Pieces.Movements.KingMovement;
import ooga.logic.board.Pieces.Movements.Movement;
import ooga.logic.board.Pieces.Movements.PawnMovement;

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
    setMyCoordinate(new GameCoordinate(xPosition,yPosition));
    updateRankAndFile();
    setMyMovement(new KingMovement());
    setMyCapture(new KingMovement());
  }

}
