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
    public List<List<Coordinate>> filterList(List<List<Coordinate>> allMoves)
    {
        List<List<Coordinate>> possibleMoves= new ArrayList<>();
        for (List<Coordinate> miniList : allMoves){
            List<Coordinate> filteredList = new ArrayList<>();
            miniList.stream()
                .filter(move -> move.getX_pos()<width && move.getX_pos()>=0 && move.getY_pos()<height&& move.getY_pos()>=0)
                .collect(Collectors.toCollection(() -> filteredList));
            possibleMoves.add(filteredList);

        }


//        List<Coordinate> possibleMoves = new ArrayList<>();
//        for (Coordinate c: allMoves) {
//            if(c.getX_pos()<width&&c.getX_pos()>=0&&c.getY_pos()<height&&c.getY_pos()>=0) {
//                possibleMoves.add(c);
//            }
//        }
        return possibleMoves;
    }
}
