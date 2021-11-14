package ooga.logic.board.board;


import ooga.logic.board.Piece;

import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.spot.GameSpot;
import ooga.logic.board.spot.Spot;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GameBoard implements Board {
    List<Spot> board;
    private int rows;
    private int columns;
    private Spot[][] spotArr;
    private String pieceName;
    private int team;
    private static final String PIECES_PACKAGE =
            GameBoard.class.getPackageName() + ".resources.";
    private static final String STRING_TO_PIECE_MAP = "PieceMap";
    private ResourceBundle pieceMap;


    public GameBoard(int rows, int columns)
    {
        board=new ArrayList<>();
        this.rows=rows;
        this.columns=columns;
        spotArr=new Spot[rows][columns];
        pieceMap=ResourceBundle.getBundle(PIECES_PACKAGE+STRING_TO_PIECE_MAP);
    }

    @Override
    public void setupBoard(String[][] setup)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<columns;j++)
            {
                if((i+j)%2==0)
                {
                    pieceName=pieceMap.getString(setup[i][j].substring(0,1));
                    team=(int)setup[i][j].charAt(1);
//                    spotArr[i][j]=new GameSpot((Piece) Class.forName(pieceName).getConstructor()
//                            .newInstance(team), j,i,0,false);
                    board.add(new GameSpot((Piece) Class.forName(pieceName).getConstructor()
                            .newInstance(team,j,i),j,i,0,false));
                }
                else{
                    pieceName=pieceMap.getString(setup[i][j].substring(0,1));
                    team=(int) setup[i][j].charAt(1);
                    board.add(new GameSpot((Piece) Class.forName(pieceName).getConstructor()
                            .newInstance(team),j,i,0,true));
//                    spotArr[i][j]=new GameSpot((Piece) Class.forName(pieceName).getConstructor()
//                            .newInstance(team),j,i,0,true);
                }

            }
        }
    }

    @Override
    public List<Spot> getFullBoard()
    {
        return board;
    }

    public void updateBoard(List<Spot> updated)
    {
        this.board=updated;
    }

    public boolean hasPiece(Coordinate c)
    {
        for (Spot s:board) {
            if (s.getCoordinate().equals(c)&&s.getPiece()!=null) {
                return true;
            }
        }
        return false;
    }


}
