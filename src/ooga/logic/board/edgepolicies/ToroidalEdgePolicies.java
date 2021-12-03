package ooga.logic.board.edgepolicies;

import java.lang.StackWalker.Option;
import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import ooga.logic.board.coordinate.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class ToroidalEdgePolicies implements EdgePolicies{
    private int height;
    private int width;
    public ToroidalEdgePolicies(int height, int width)
    {
        this.width=width;
        this.height=height;
    }

    public List<Coordinate> filterList(List<Coordinate> allMoves)
    {
        List<Coordinate> possibleMoves=new ArrayList<>();
        for (Coordinate c: allMoves)
        {
            toroidalX(c);
            toroidalY(c);
            possibleMoves.add(c);
        }
        return possibleMoves;
    }

    public void toroidalX(Coordinate c)
    {

//        Map<String, Consumer<List<Integer>>> fitMap = Map.of(WORST_FIT,integers -> {},
//            WORST_FIT_DECREASING, Collections::shuffle, WORST_FIT_RANDOM, integers -> integers.sort(Collections.reverseOrder()));
//
//        Map<String, Consumer<List<Integer>>> fitMap = Map.of(
//            WORST_FIT,integers -> {},
//            WORST_FIT_DECREASING,Collections::shuffle,
//            WORST_FIT_RANDOM, integers -> integers.sort(Collections.reverseOrder()));

//
//        Stream<Integer> greaterThanWidth = i -> c.getX_pos() >= width;
//        Stream<Integer> lessThanZero = i -> c.getX_pos() < 0;

//        Option.of(c.getX_pos()).filter()
        if(c.getX_pos()>=width) {
            c.setX_pos(c.getX_pos()-width);
        }
        else if(c.getX_pos()<0)
        {
            c.setX_pos(c.getX_pos()+width);
        }
    }

    public void toroidalY(Coordinate c)
    {
        if(c.getY_pos()>=height)
        {
            c.setY_pos(c.getY_pos()-height);
        }
        else if(c.getY_pos()<0)
        {
            c.setY_pos(c.getY_pos()+height);
        }
    }
}
