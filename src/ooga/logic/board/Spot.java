package ooga.logic.board;

import ooga.logic.board.Pieces.Piece;

public interface Spot {
    public void setCoordinate(int x, int y);
    public Coordinate getCoordinate();
    public void setPiece(Piece piece);
    public Piece getPiece();
}
