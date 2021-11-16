package ooga.logic.board.edgepolicies;

import ooga.logic.board.coordinate.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class ToroidalEdgePolicies implements EdgePolicies{
    private List<Coordinate> allMoves;
    private int height;
    private int width;
    public ToroidalEdgePolicies(List<Coordinate> allMoves, int height, int width)
    {
        this.allMoves=allMoves;
        this.width=width;
        this.height=height;
    }

    public List<Coordinate> filterList()
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
        if(c.getX_pos()>=width)
        {
            c.setX_pos(c.getX_pos()-width);
        }
        else if(c.getX_pos()<0)
        {
            c.setX_pos(c.getX_pos()+width);
        }
    }

    public void toroidalY(Coordinate c)
    {
        if(c.getX_pos()>=height)
        {
            c.setX_pos(c.getX_pos()-height);
        }
        else if(c.getX_pos()<0)
        {
            c.setX_pos(c.getX_pos()+height);
        }
    }
}
