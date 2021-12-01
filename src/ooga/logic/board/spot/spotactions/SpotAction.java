package ooga.logic.board.spot.spotactions;

import ooga.logic.board.Pieces.PieceBundle.Piece;
import ooga.logic.board.spot.Spot;

import java.util.List;

public interface SpotAction {
    public void commitSpotAction(List<Spot> a, Spot s);
}
