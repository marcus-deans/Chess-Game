package ooga.logic.board.edgepolicies;

import java.util.stream.Collectors;
import ooga.logic.board.coordinate.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class BasicEdgePolicies implements EdgePolicies{
    private int height;
    private int width;

    public BasicEdgePolicies(int height, int width)
    {
        this.width=width;
        this.height=height;
    }

    @Override
    public List<Coordinate> filterList(List<Coordinate> allMoves)
    {
        List<Coordinate> possibleMoves= allMoves.stream()
                .filter(coord -> coord.getX_pos()<width && coord.getX_pos() >= 0)
            .filter(coord -> coord.getY_pos() < height && coord.getY_pos() >= 0)
            .collect(Collectors.toList());
//            .forEach(coord -> possibl);
//            new ArrayList<>();
//        for (Coordinate c: allMoves)
//        {
//            if(c.getX_pos()<width&&c.getX_pos()>=0&&c.getY_pos()<height&&c.getY_pos()>=0)
//            {
//                possibleMoves.add(c);
//            }
//        }
        return possibleMoves;
    }
}
