package ooga.logic.board.edgepolicies;

import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ToroidalEdgePoliciesTest {
    @Test
    public void allWithinBoundTest()
    {
        List<Coordinate> a=new ArrayList<>();
        List<Coordinate> c;
        a.add(new GameCoordinate(3,4));
        a.add(new GameCoordinate(0,0));
        a.add(new GameCoordinate(2,1));
        ToroidalEdgePolicies b=new ToroidalEdgePolicies(5,5);
        c=b.filterList(a);
        Assertions.assertEquals(a.size(),c.size());
    }

    @Test
    public void xOutsideBoundTest()
    {
        List<Coordinate> a=new ArrayList<>();
        List<Coordinate> c;
        a.add(new GameCoordinate(3,4));
        a.add(new GameCoordinate(6,0));
        a.add(new GameCoordinate(-2,0));
        a.add(new GameCoordinate(2,1));
        ToroidalEdgePolicies b=new ToroidalEdgePolicies(5,5);
        c=b.filterList(a);
        Assertions.assertTrue(c.get(1).getX_pos()==1&&c.get(2).getX_pos()==3);
    }

    @Test
    public void yOutsideBoundTest()
    {
        List<Coordinate> a=new ArrayList<>();
        List<Coordinate> c;
        a.add(new GameCoordinate(0,5));
        a.add(new GameCoordinate(0,-2));
        ToroidalEdgePolicies b=new ToroidalEdgePolicies(5,5);
        c=b.filterList(a);
        Assertions.assertTrue(c.get(0).getY_pos()==0&&c.get(1).getY_pos()==3);
    }
}
