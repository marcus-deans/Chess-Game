package ooga.logic.board.edgepolicies;

import ooga.logic.board.coordinate.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class BasicEdgePolicies {
    private List<Coordinate> allMoves;
    private int height;
    private int width;

    public BasicEdgePolicies(List<Coordinate> allMoves, int height, int width)
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
            if(c.getX_pos()<width&&c.getX_pos()>=0&&c.getY_pos()<height&&c.getY_pos()>=0)
            {
                possibleMoves.add(c);
            }
        }
        return possibleMoves;
    }
}
