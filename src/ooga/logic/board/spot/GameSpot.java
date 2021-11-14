package ooga.logic.board.spot;

import ooga.logic.board.Piece;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;

import java.util.Objects;

public class GameSpot implements Spot{
    private Piece piece;
    private Coordinate coordinate;
    private int typeOfSpot;
    private boolean color;
    private boolean empty;

    public GameSpot(Piece piece, int x, int y, int typeOfSpot, boolean color)
    {
        this.setPiece(piece);
        this.setCoordinate(x,y);
        this.setTypeOfSpot(typeOfSpot);
        this.color=color;
    }

    @Override
    public void setCoordinate(int x, int y) {
        this.coordinate=new GameCoordinate(x,y);
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }


    @Override
    public void setPiece(Piece piece) {
        this.piece=piece;
        if (piece==null)
        {
            this.empty=true;
        }
    }

    @Override
    public Piece getPiece() {
        return piece;
    }

    public int getTypeOfSpot() {
        return typeOfSpot;
    }

    public void setTypeOfSpot(int typeOfSpot) {
        this.typeOfSpot = typeOfSpot;
    }

    public void emptySpot()
    {
        this.piece=null;
        this.empty=true;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameSpot spot = (GameSpot) o;
        return Objects.equals(piece,spot.piece) && coordinate.getX_pos() == spot.coordinate.getX_pos()
                && coordinate.getY_pos() == spot.coordinate.getY_pos() && typeOfSpot == spot.typeOfSpot;
    }

    @Override
    public int hashCode () {
        return Objects.hash(piece, coordinate.getX_pos(),coordinate.getY_pos(), typeOfSpot);
    }


}
