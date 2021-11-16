package ooga.logic.board.board;


import ooga.logic.board.Pieces.PieceBundle.Piece;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;
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
    private static final String PIECE_PATH="ooga.logic.board.Pieces.PieceBundle.";


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
                System.out.println(setup[i][j]);
                System.out.println(j);
                pieceName=PIECE_PATH+pieceMap.getString(setup[i][j].substring(0,1));
                Class[] params={int.class,int.class,int.class};
                team=Integer.parseInt(setup[i][j].substring(1,2));
                if((i+j)%2==0)
                {
//                    spotArr[i][j]=new GameSpot((Piece) Class.forName(pieceName).getConstructor()
//                            .newInstance(team), j,i,0,false);
                    Piece p=(Piece) Class.forName(pieceName).getDeclaredConstructor(params).newInstance(team,j,i);
                    board.add(new GameSpot(p,j,i,0,false));
                }
                else{
//                    board.add(new GameSpot((Piece) Class.forName(pieceName).getConstructor()
//                            .newInstance(team),j,i,0,true));
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


    public List<GameCoordinate> getPossibleCoordinates(GameCoordinate selected) {
        return null;
    }

    public Boolean getIsJump(GameCoordinate selected) {
        return false;
    }
}
