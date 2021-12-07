package ooga.logic.board.edgepolicies;

import ooga.logic.board.coordinate.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class CheatCodeEdgePolicies implements EdgePolicies{
    private int height;
    private int width;
    public CheatCodeEdgePolicies(int height, int width)
    {
        this.height=height;
        this.width=width;
    }

    /**
     *
     * @param allMoves
     * @return list of moves that are allowed by this edge policy. All boundaries are toroidal
     */
    public List<List<Coordinate>> filterList(List<List<Coordinate>> allMoves)
    {
        List<List<Coordinate>> possibleMoves=new ArrayList<>();
        for (List<Coordinate> eachList: allMoves){
            List<Coordinate> changedList = eachList.stream().
                    map(c -> {
                        toroidalX(c);
                        toroidalY(c);
                        return c;
                    }).collect(Collectors.toList());


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
     * If the y positions of the possible move are beyond the y-boundaries, then it adds or subtracts
     * the height of the board to make the position wrap to the other side
     * @param c
     */
    public void toroidalY(Coordinate c)
    {
        Consumer<Coordinate> alterX = list ->
        {
            if (c.getY_pos() >= height){
                c.setY_pos(c.getY_pos()-height);
            }
            else if(c.getY_pos()<0)
            {
                c.setY_pos(c.getY_pos()+height);
            }
        };
        alterX.accept(c);
    }
}
