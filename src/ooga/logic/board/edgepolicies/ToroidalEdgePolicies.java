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

    public List<List<Coordinate>> filterList(List<List<Coordinate>> allMoves)
    {
        List<List<Coordinate>> possibleMoves=new ArrayList<>();
        for (List<Coordinate> eachList: allMoves){
            List<Coordinate> changedList = new ArrayList<>();
            for (Coordinate c: eachList)
            {
                toroidalX(c);
                toroidalY(c);
                changedList.add(c);
            }
            possibleMoves.add(changedList);
        }

        return possibleMoves;
    }

    public void toroidalX(Coordinate c)
    {

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
