package ooga.logic.board.edgepolicies;

import ooga.logic.board.coordinate.Coordinate;

import java.util.List;

public interface EdgePolicies {
    List<List<Coordinate>> filterList(List<List<Coordinate>> allMoves);
}
