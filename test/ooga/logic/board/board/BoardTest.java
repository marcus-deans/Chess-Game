package ooga.logic.board.board;

import ooga.logic.board.Pieces.PieceBundle.King;
import ooga.logic.board.coordinate.GameCoordinate;
import ooga.logic.board.spot.GameSpot;
import ooga.logic.board.spot.Spot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class BoardTest {
    @Test
    public void boardSetup() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String[][] a={{"K1","K2"}};
        Board b=new GameBoard(1,2);
        b.setupBoard("K1",0,0);
        b.setupBoard("K2",0,1);
        List<Spot> c=b.getFullBoard();
        Spot s1=new GameSpot(new King(1,0,0),0,0,0,true);
        Spot s2=new GameSpot(new King(1,1,0),1,0,0,false);
        Assertions.assertTrue(s1.equals(c.get(0))&&s2.equals(c.get(1)));
    }

    @Test
    public void hasPiece() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String[][] a={{"K1","K2"}};
        Board b=new GameBoard(1,2);
        b.setupBoard(a[0][1],0,1);
        b.setupBoard(a[0][0],0,0);
        List<Spot> c=b.getFullBoard();
        Assertions.assertTrue(((GameBoard) b).hasPiece(new GameCoordinate(0,0)));
    }

//    @Test
//    public void hasNoPiece() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//        String[][] a={{"K1","K2"}};
//        Board b=new GameBoard(1,2);
//        b.setupBoard(a[0][1],0,1);
//        b.setupBoard(a[0][0],0,0);
//        List<Spot> c=b.getFullBoard();
//        c.add(new GameSpot(null,1,1,0,true));
//        ((GameBoard) b).updateBoard(c);
//        Assertions.assertFalse(((GameBoard) b).hasPiece(new GameCoordinate(1,1)));
//
//    }
}
