package ooga.logic.board.spot;

import ooga.logic.board.Pieces.PieceBundle.Piece;
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

    /**
     * Sets the coordinate of the spot
     * @param x
     * @param y
     */
    @Override
    public void setCoordinate(int x, int y) {
        this.coordinate=new GameCoordinate(x,y);
    }

    /**
     *
     * @return coordinate of the spot
     */
    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * sets the piece of the spot, if the piece is null, then sets empty to true
     * @param piece
     */
    @Override
    public void setPiece(Piece piece) {
        this.piece=piece;
        if (piece==null)
        {
            this.empty=true;
        }
    }

    /**
     *
     * @return empty
     */
    @Override
    public boolean isEmpty() {
        return empty;
    }

    /**
     *
     * @return piece
     */
    @Override
    public Piece getPiece() {
        return piece;
    }

    /**
     *
     * @return typeOfSpot
     */
    public int getTypeOfSpot() {
        return typeOfSpot;
    }

    /**
     *
     * @return color
     */
    public boolean getColor(){return color;}

    /**
     * sets type of spot
     * @param typeOfSpot
     */
    public void setTypeOfSpot(int typeOfSpot) {
        this.typeOfSpot = typeOfSpot;
    }

//    public void emptySpot()
//    {
//        this.piece=null;
//        this.empty=true;
//    }

    /**
     * checks if two spots are equal
     * @param o
     * @return
     */
    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameSpot spot = (GameSpot) o;
        return  this.coordinate.getX_pos() == spot.coordinate.getX_pos()
                && this.coordinate.getY_pos() == spot.coordinate.getY_pos()
                && this.typeOfSpot == spot.typeOfSpot && this.color==spot.color;
                //&& Objects.equals(this.piece,spot.piece);
    }



    @Override
    public int hashCode () {
        return Objects.hash(piece, coordinate.getX_pos(),coordinate.getY_pos(), typeOfSpot);
    }


}
