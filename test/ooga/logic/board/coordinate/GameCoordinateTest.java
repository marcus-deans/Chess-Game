package ooga.logic.board.coordinate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameCoordinateTest {
    @Test
    public void getPos()
    {
        Coordinate c=new GameCoordinate(0,0);
        Coordinate c1=new GameCoordinate(0,0);
        c1.setX_pos(1);
        c1.setY_pos(1);
        Assertions.assertTrue(c.getX_pos()==0&&c1.getX_pos()==1&&
                c.getY_pos()==0&&c1.getY_pos()==1);
    }

    @Test
    public void equals()
    {
        Coordinate c=new GameCoordinate(0,0);
        Coordinate c1=new GameCoordinate(0,0);
        Assertions.assertTrue(c.equals(c1));
    }

    @Test
    public void setPos()
    {
        Coordinate c=new GameCoordinate(0,0);
        c.setCoordinate(1,1);
        Assertions.assertTrue(c.getX_pos()==1&&c.getY_pos()==1);
    }

    @Test
    public void setCoor()
    {
        Coordinate c=new GameCoordinate(0,0);
        c.setCoordinate(new GameCoordinate(1,1));
        Assertions.assertTrue(c.getX_pos()==1&&c.getY_pos()==1);
    }
}