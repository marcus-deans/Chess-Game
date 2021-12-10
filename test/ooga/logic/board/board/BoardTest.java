package ooga.logic.board.board;


import java.util.HashMap;
import java.util.Map;
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
        Board b=new GameBoard(1,2, new HashMap<>());
        Map<String,String> myMap = new HashMap<>();
        b.setupBoard("K10",0,0);
        b.setupBoard("K20",0,1);
        List<Spot> c=b.getFullBoard();
        Spot s1=new GameSpot(new King(1,0,0),0,0,0,true);
        Spot s2=new GameSpot(new King(1,1,0),1,0,0,false);
        Assertions.assertTrue(s1.equals(c.get(0))&&s2.equals(c.get(1)));
    }

    @Test
    public void hasPiece() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String[][] a={{"K10","K20"}};
        Board b=new GameBoard(1,2, new HashMap<>());
        b.setupBoard(a[0][1],0,1);
        b.setupBoard(a[0][0],0,0);
        Assertions.assertTrue(((GameBoard) b).hasPiece(new GameCoordinate(0,0)));
    }

    @Test
    public void hasNoPiece() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Board b=new GameBoard(1,2,new HashMap<>());
        b.setupBoard("E00",0,0);
        Assertions.assertFalse(((GameBoard) b).hasPiece(new GameCoordinate(0,0)));
    }

    @Test
    public void newSpotContainsPiece() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Board b=new GameBoard(2,2,new HashMap<>());
        b.setupBoard("K10",0,0);
        b.setupBoard("E00",0,1);
        b.setupBoard("E00",1,0);
        b.setupBoard("E00",1,1);
        b.updateBoard(new GameCoordinate(1,1),b.getFullBoard().get(0).getPiece());
        Assertions.assertTrue(((GameBoard) b).hasPiece(new GameCoordinate(1,1)));
    }

    @Test
    public void portalSpotHasNoPiece() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Board b=new GameBoard(2,2,new HashMap<>());
        b.setupBoard("K10",0,0);
        ((GameBoard) b).getSpot(new GameCoordinate(0,0)).setPiece(null);
        b.setupBoard("E00",0,1);
        b.setupBoard("E00",1,0);
        b.setupBoard("E01",1,1);
        b.updateBoard(new GameCoordinate(1,1),b.getFullBoard().get(0).getPiece());
        Assertions.assertFalse(((GameBoard) b).hasPiece(new GameCoordinate(1,1)));
    }

    @Test
    public void portalSpotMoved() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Board b=new GameBoard(2,2,new HashMap<>());
        b.setupBoard("K10",0,0);
        ((GameBoard) b).getSpot(new GameCoordinate(0,0)).setPiece(null);
        b.setupBoard("E00",0,1);
        b.setupBoard("E00",1,0);
        b.setupBoard("E00",1,1);
        b.updateBoard(new GameCoordinate(1,1),b.getFullBoard().get(0).getPiece());
        Assertions.assertFalse(((GameBoard) b).getSpot(new GameCoordinate(1,1)).getTypeOfSpot()==1);
    }

    @Test
    public void reset() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Board b=new GameBoard(2,2,new HashMap<>());
        b.setupBoard("K10",0,0);
        //((GameBoard) b).getSpot(new GameCoordinate(0,0)).setPiece(null);
        b.setupBoard("E00",0,1);
        b.setupBoard("E00",1,0);
        b.setupBoard("E00",1,1);
        b.updateBoard(new GameCoordinate(1,1),b.getFullBoard().get(0).getPiece());
        ((GameBoard)b).reset();
        //System.out.println(((GameBoard) b).getSpot(new GameCoordinate(0,0)).getPiece());
        Assertions.assertTrue(((GameBoard) b).getSpot(new GameCoordinate(1,1)).getPiece()==null);
    }
    @Test
    public void atomicTest() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Board b=new GameBoard(4,3,new HashMap<>());
        b.setupBoard("Q20",0,0);
        b.setupBoard("Q20",0,1);
        b.setupBoard("Q20",0,2);
        b.setupBoard("Q20",1,0);
        b.setupBoard("R20",1,1);
        b.setupBoard("Q10",1,2);
        b.setupBoard("Q20",2,0);
        b.setupBoard("Q20",2,1);
        b.setupBoard("Q20",2,2);
        b.setupBoard("Q20",3,0);
        b.setupBoard("Q20",3,1);
        b.setupBoard("Q20",3,2);
        ((GameBoard) b).setAtomic(true);
        b.updateBoard(new GameCoordinate(1,1),b.getFullBoard().get(5).getPiece());
        Assertions.assertTrue(b.getFullBoard().get(0).getPiece()==null && b.getFullBoard().get(10).getPiece()!=null);
        for (int i = 0; i  < 2; i++){
            for (int j = 0; j < 2; j++){
                Assertions.assertTrue(b.getFullBoard().get(i*7 + j).getPiece() == null);
            }
        }
    }

}
