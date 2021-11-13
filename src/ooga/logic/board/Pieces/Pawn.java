package ooga.logic.board.Pieces;


import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.Coordinate;
import ooga.logic.board.CoordinateUseCase;

/**
 * Implement a Pawn that can do the following
 * Promote on the 8th rank
 *
 */
public class Pawn implements Piece,Moves,Captures,Promotes {




  private int state=1;//Represents a pawn
  private List<Coordinate> possibleMoves=new ArrayList<>();
  private Coordinate currentLoc=new CoordinateUseCase(0,0);
  private List<Coordinate> possibleChanges=new ArrayList<>();

  @Override
  public void captures() {

  }

  @Override
  public List<Coordinate> possibleCaptures() {
    return null;
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
  public void possiblePromotionPieces(){

  }

}
