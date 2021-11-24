package ooga.logic.board.spot;

import ooga.logic.board.Pieces.PieceBundle.Piece;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;

public interface Spot {
    public void setCoordinate(int x, int y);
    public Coordinate getCoordinate();
    public void setPiece(Piece piece);
    public Piece getPiece();
    public boolean isEmpty();
}
