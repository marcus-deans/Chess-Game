package ooga.logic.board.edgepolicies;

import java.lang.StackWalker.Option;
import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
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

    /**
     *
     * @param allMoves
     * @return list of moves that are allowed by this edge policy. The sides of the game are
     * toroidal but the top and bottom are not
     */
    public List<List<Coordinate>> filterList(List<List<Coordinate>> allMoves)
    {
        List<List<Coordinate>> possibleMoves=new ArrayList<>();
        for (List<Coordinate> eachList: allMoves){
            List<Coordinate> changedList = eachList.stream().
                map(c -> {
                    toroidalX(c);
                    return c;
                }).collect(Collectors.toList());

            changedList=toroidalY(changedList);
            possibleMoves.add(changedList);
        }

        return possibleMoves;
    }

    /**
     * If the x positions of the possible move are beyond the x-boundaries, then it adds or subtracts
     * the width of the board to make the position wrap to the other side
     * @param c
     */
    public void toroidalX(Coordinate c)
    {
        Consumer<Coordinate> alterX = list ->
        {
            if (c.getX_pos() >= width){
                c.setX_pos(c.getX_pos()-width);
            }
            else if(c.getX_pos()<0)
            {
                c.setX_pos(c.getX_pos()+width);
            }
        };
        alterX.accept(c);

    }

    /**
     *
     * @param a
     * @return list of coordinates that are within the y-boundaries
     */
    public List<Coordinate> toroidalY(List<Coordinate> a)
    {


        List<Coordinate> b=new ArrayList<>();
        for (Coordinate c: a)
        {
            if(c.getY_pos()<height&& c.getY_pos()>=0)
            {
                b.add(c);
            }
        }
        return b;

    }
}
