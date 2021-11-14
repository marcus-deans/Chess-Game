package ooga.logic.board;

import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.CoordinateUseCase;

import java.util.ArrayList;
import java.util.List;

public class PieceUseCase implements Piece{
    private int state=1;//Represents a pawn
    private List<Coordinate> possibleMoves=new ArrayList<>();
    private Coordinate currentLoc=new CoordinateUseCase(0,0);
    private List<Coordinate> possibleChanges=new ArrayList<>();

    /**
     * This use case will demonstrate how we will derive the possible moves from any piece
     */
    @Override
    public void move()
    {
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
    public Coordinate getCoordinate() {
        return null;
    }

    @Override
    public void setCoordinate() {

    }

    @Override
    public void setState() {

    }

    @Override
    public void updatePosition() {

    }



}
