package ooga.logic.board.edgepolicies;

import ooga.logic.board.coordinate.Coordinate;

import java.util.List;

public interface EdgePolicies {
    public List<Coordinate> filterList(List<Coordinate> allMoves);
}
