package ooga.logic.board.board;


import ooga.logic.board.Pieces.PieceBundle.Piece;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.coordinate.GameCoordinate;
import ooga.logic.board.edgepolicies.EdgePolicies;
import ooga.logic.board.spot.GameSpot;
import ooga.logic.board.spot.Spot;
import ooga.logic.board.spot.spotactions.SpotAction;
import ooga.logic.game.Game;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GameBoard implements Board {
    List<Spot> board;
    private int rows;
    private int columns;
    private String pieceName;
    private int team;
    private static final String PIECES_PACKAGE =
            GameBoard.class.getPackageName() + ".resources.";

    private static final String GAMEBOARD_MAP = "GameBoard";
    private ResourceBundle resourceMap;
    private int spotType;
    private static final String PIECE_PATH="ooga.logic.board.Pieces.PieceBundle.";
    private static final String EDGEPOLICY_PATH="ooga.logic.board.edgepolicies.";
    private EdgePolicies edgePolicy;
    private String edgeName;
    private static final String SPOTACTION_PACKAGE =
            Game.class.getPackageName() + ".resources.";
    private static final String SPOTACTION_MAP = "Spot";
    private static final String SPOTACTION_PATH="ooga.logic.board.spot.spotactions.";
    private String spotActionName;


    public GameBoard(int rows, int columns)
    {
        board=new ArrayList<>();
        this.rows=rows;
        this.columns=columns;
        resourceMap=ResourceBundle.getBundle(PIECES_PACKAGE+GAMEBOARD_MAP);
    }

    @Override
    public void setupBoard(String spot, int i, int j) {
            pieceName=PIECE_PATH+resourceMap.getString(spot.substring(0,1));
            Class[] params={int.class,int.class,int.class};
            team=Integer.parseInt(spot.substring(1,2));
            int type=Integer.parseInt(spot.substring(2,3));
            Piece p;
            try
            {
                p=(Piece) Class.forName(pieceName).getDeclaredConstructor(params).newInstance(j,i,team);
            }
            catch(Exception e)
            {
                p=null;
            }

            board.add(new GameSpot(p,j,i,type,(i+j)%2==0));
    }

    @Override
    public List<Spot> getFullBoard()
    {
        return board;
    }

    public void updateBoard(Coordinate newPosition, Piece movingPiece)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        getSpot(newPosition).setPiece(movingPiece);
        int spotType=getSpot(newPosition).getTypeOfSpot();
        spotActionName=SPOTACTION_PATH+resourceMap.getString(String.valueOf(spotType));
        SpotAction spotAction=(SpotAction) Class.forName(spotActionName).getConstructor().newInstance();
        this.board=spotAction.commitSpotAction(board,getSpot(newPosition));
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

    public List<GameCoordinate> getPossibleCoordinates() {
        return new ArrayList<>();
    }

    public Boolean getIsJump(GameCoordinate selected) {
        return false;
    }



    public GameSpot getSpot(Coordinate selected) {
        for (Spot s : board)
        {
            if (s.getCoordinate().equals(selected))
            {
                return (GameSpot) s;
            }
        }
        return null;
    }

    public void setEdgePolicy(String edge) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class[] params={int.class,int.class};
        edgeName=EDGEPOLICY_PATH+resourceMap.getString(edge);
        edgePolicy=(EdgePolicies) Class.forName(edgeName).getDeclaredConstructor(params).newInstance(rows,columns);
    }

    public EdgePolicies getEdgePolicy() {
        return edgePolicy;
    }

    public void reset(List<Spot> newList){
        this.board = newList;
    }
}
