package ooga.logic.board;

public interface Spot {
    public void setCoordinate(int x, int y);
    public Coordinate getCoordinate();
    public void setPiece(Piece piece);
    public Piece getPiece();
}
