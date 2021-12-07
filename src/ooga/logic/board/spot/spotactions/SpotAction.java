package ooga.logic.board.spot.spotactions;

import ooga.logic.board.spot.Spot;

import java.util.List;

public interface SpotAction {
    public List<Spot> commitSpotAction(List<Spot> a, Spot s);
}
