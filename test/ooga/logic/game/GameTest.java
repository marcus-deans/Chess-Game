package ooga.logic.game;

import java.util.Set;
import ooga.logic.board.Pieces.PieceBundle.King;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;
import ooga.logic.board.spot.GameSpot;
import ooga.logic.board.spot.Spot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

public class GameTest {
    @Test
    public void intializeNewGame() throws IOException {
        Game testGame = new Game(10, 10, new HashMap<>());
        Assertions.assertEquals(null, testGame.getFullBoard());
    }

    @Test
    public void setUpBoardTest() throws IOException {
        Game testGame = new Game(10, 10, new HashMap<>());
        Spot s1=new GameSpot(new King(1,0,0),0,0,0,true);
        Spot s2=new GameSpot(new King(1,1,0),1,0,0,false);
        testGame.setupBoard("K10", 0, 0);
        testGame.setupBoard("K20", 0, 1);
        List<Spot> c = testGame.getFullBoard();

        Assertions.assertTrue(s1.equals(c.get(0))&&s2.equals(c.get(1)));
    }

    @Test
    public void boardResetTest() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Game testGame = new Game(10, 10, new HashMap<>());
        testGame.setupBoard("K10", 0, 0);
        testGame.movePiece((new GameCoordinate(0,0)), (new GameCoordinate(1,1)));

        testGame.reset();
        Assertions.assertEquals(null, testGame.getSpot(new GameCoordinate(1, 1)));
    }

    @Test
    public void getPossibleMovesTest() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Game testGame = new Game(2,2,new HashMap<>());
        testGame.setEdgePolicy("Basic");
        testGame.setupBoard("K10",0,0);
        testGame.setupBoard("R20",1,0);
        testGame.setupBoard("R10",0,1);
        testGame.setupBoard("R20",1,1);
        testGame.setupBoard("R10",0, 2);
        testGame.setupBoard("R10",2, 0);
        testGame.setupBoard("R10",1, 2);
        testGame.setupBoard("R10",2, 1);
        Coordinate myCoord = new GameCoordinate(0,0);
        Set<Spot> mySetOfSpots= testGame.getPossibleCoordinates(myCoord,1);
        assertEquals(mySetOfSpots.size(),2);
    }

    @Test
    public void movePieceTest() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Game testGame = new Game(2,2,new HashMap<>());
        testGame.setEdgePolicy("Basic");
        testGame.setupBoard("K10",0,0);
        testGame.setupBoard("R20",1,0);
        testGame.setupBoard("E00",0,1);
        testGame.setupBoard("R20",1,1);
        testGame.setupBoard("R10",0, 2);
        testGame.setupBoard("R10",2, 0);
        testGame.setupBoard("R10",1, 2);
        testGame.setupBoard("R10",2, 1);
        Coordinate oldCoord = new GameCoordinate(0,0);
        Coordinate newCoord = new GameCoordinate(0,1);
        testGame.movePiece(oldCoord, newCoord);
        Assertions.assertEquals("King", testGame.getSpot(newCoord).getPiece().getPieceName());
    }

    @Test
    public void isGameOverTestFalse() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Game testGame = new Game(2,2,new HashMap<>());
        testGame.setEdgePolicy("Basic");
        testGame.setupBoard("R20",0,0);
        testGame.setupBoard("R20",1,0);
        testGame.setupBoard("K10",0,1);
        testGame.setupBoard("R20",1,1);
        testGame.setupBoard("R10",0, 2);
        testGame.setupBoard("R10",2, 0);
        testGame.setupBoard("R10",1, 2);
        testGame.setupBoard("R10",2, 1);
        Coordinate oldCoord = new GameCoordinate(0,0);
        Coordinate newCoord = new GameCoordinate(0,1);
        testGame.movePiece(oldCoord, newCoord);
        Assertions.assertFalse(testGame.getIsGameOver());
    }

    @Test
    public void isGameOverTestTrue() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Game testGame = new Game(2,2,new HashMap<>());
        testGame.setEdgePolicy("Basic");
        testGame.setGameType("Standard");
        testGame.setupBoard("R20",0,0);
        testGame.setupBoard("R20",1,0);
        testGame.setupBoard("K10",0,1);
        testGame.setupBoard("R20",1,1);
        testGame.setupBoard("R10",0, 2);
        testGame.setupBoard("R10",2, 0);
        testGame.setupBoard("R10",1, 2);
        testGame.setupBoard("R10",2, 1);
        Coordinate oldCoord = new GameCoordinate(0,0);
        Coordinate newCoord = new GameCoordinate(0,1);
        testGame.movePiece(oldCoord, newCoord);
        Assertions.assertTrue(!testGame.getIsGameOver());
    }
}

