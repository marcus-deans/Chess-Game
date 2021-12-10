package ooga.logic.board.board;


import java.util.Map;
import ooga.logic.board.Pieces.PieceBundle.Piece;
import ooga.logic.board.coordinate.Coordinate;
import ooga.logic.board.edgepolicies.EdgePolicies;
import ooga.logic.board.spot.GameSpot;
import ooga.logic.board.spot.Spot;
import ooga.logic.board.spot.spotactions.SpotAction;
import ooga.logic.game.Game;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameBoard implements Board {
    private List<Spot> board;
    private List<Spot> initialBoard;
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
    private Logger myLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private boolean isAtomic;
    private Map<String,String> myRules;


    public GameBoard(int rows, int columns, Map<String,String> overridingRules)
    {
        initialBoard=new ArrayList<>();
        this.rows=rows;
        this.columns=columns;
        this.myRules = overridingRules;
        resourceMap=ResourceBundle.getBundle(PIECES_PACKAGE+GAMEBOARD_MAP);
    }

    /**
     * Method takes in a string, spot, and two integers i and j. The string is made up of 3 characters
     * which are reflectively sets up a spot and adds it to board. The first character in spot is the
     * piece, the second is the team, and the third is the spot type. i and j refer to the position of
     * the spot on the board
     * @param spot
     * @param i
     * @param j
     */
    @Override
    public void setupBoard(String spot, int i, int j) {
         pieceName=PIECE_PATH+resourceMap.getString(spot.substring(0,1));
            Class[] params={int.class,int.class,int.class,Map.class};
            team=Integer.parseInt(spot.substring(1,2));
            int type=Integer.parseInt(spot.substring(2,3));
            Piece p;
            try
            {
                p=(Piece) Class.forName(pieceName).getDeclaredConstructor(params).newInstance(j,i,team,myRules);
            }
            catch(Exception e)
            {
                p=null;
            }


        initialBoard.add(new GameSpot(p,j,i,type,(i+j)%2==0));
        copyInitialBoard(initialBoard);
        //Collections.copy(board,initialBoard);
    }

    /**
     * copyInitialBoard copies over the elements from the initial board into the board that is used
     * when movements are made in the game
     * @param initial
     */
    private void copyInitialBoard(List<Spot> initial)
    {
        board=new ArrayList<>();
        for (Spot a: initial)
        {
            board.add(new GameSpot(a.getPiece(),a.getCoordinate().getX_pos(), a.getCoordinate().getY_pos()
                    ,((GameSpot) a).getTypeOfSpot(),((GameSpot) a).getColor()));
        }
    }

    /**
     *
     * @return board
     */
    @Override
    public List<Spot> getFullBoard()
    {
        return board;
    }

    /**
     * updateBoard takes in a position that a piece is moving to and the piece. If the game mode is atomic
     * then the method clears out the atomic radius, if not it sets the newPosition equal to the movingPiece.
     * It then looks at the type of spot and does the proper spotAction reflectively
     * @param newPosition
     * @param movingPiece
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void updateBoard(Coordinate newPosition, Piece movingPiece)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(isAtomic && getSpot(newPosition).getPiece()!=null)
        {
            atomic(newPosition);
        }
        else
        {
            getSpot(newPosition).setPiece(movingPiece);
        }
        int spotType=getSpot(newPosition).getTypeOfSpot();
        spotActionName=SPOTACTION_PATH+resourceMap.getString(String.valueOf(spotType));
        SpotAction spotAction=(SpotAction) Class.forName(spotActionName).getConstructor().newInstance();
        this.board=spotAction.commitSpotAction(board,getSpot(newPosition));
    }

    /**
     * Clears out the atomicArea at the coordinate
     * @param newPosition
     */
    private void atomic(Coordinate newPosition){
        List<List<Coordinate>> list=getSpot(newPosition).getPiece().getAtomicArea().getPossibleSpots(newPosition);
        getSpot(newPosition).setPiece(null);

        for (List<Coordinate> myList : list){
            for(Coordinate eachCoord : myList){
                Spot thisSpot = getSpot(eachCoord);
                if (thisSpot.isEmpty()){
                    continue;
                }
                if (!thisSpot.getPiece().getAtomicImmunity()){
                    thisSpot.setPiece(null);
                }
            }
        }
    }


    /**
     *
     * @param c
     * @return true if the spot at the coordinate c has a piece, else false
     */
    public boolean hasPiece(Coordinate c)
    {
        for (Spot s:board) {
            if (s.getCoordinate().equals(c)&&s.getPiece()!=null) {
                return true;
            }
        }

        return false;
    }

//    public List<GameCoordinate> getPossibleCoordinates() {
//        return new ArrayList<>();
//    }
//
//    public Boolean getIsJump(GameCoordinate selected) {
//        return false;
//    }


    /**
     *
     * @param selected
     * @return the spot at the coordinate selected, else null
     */
    public GameSpot getSpot(Coordinate selected) {
//       final Spot mySpot;
//        board.stream().filter(spot -> spot.getCoordinate().equals(selected)).
//           forEach(spot -> mySpot = spot);

        for (Spot s : board)
        {
            if (s.getCoordinate().equals(selected))
            {
                return (GameSpot) s;
            }
        }
        return null;
    }

    /**
     * Sets the edge policy reflectively using the String edge
     * @param edge
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void setEdgePolicy(String edge) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class[] params={int.class,int.class};
        edgeName=EDGEPOLICY_PATH+resourceMap.getString(edge);
        edgePolicy=(EdgePolicies) Class.forName(edgeName).getDeclaredConstructor(params).newInstance(rows,columns);
    }

    /**
     *
     * @return edgePolicy
     */
    public EdgePolicies getEdgePolicy() {
        return edgePolicy;
    }

    /**
     * copies the data from the initial board into the current board
     */
    public void reset(){
        copyInitialBoard(initialBoard);
        myLogger.log(Level.INFO, "Reset in gameboard");
    }

    /**
     * sets the boolean isAtomic to atomic which triggers the atomic method
     * @param atomic
     */
    public void setAtomic(boolean atomic)
    {
        isAtomic=atomic;
    }

    /**
     * Used for cheat codes. Sets all the pawns on the board to a specified piece using reflection
     * @param piece
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void pawnsToPiece(String piece) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class[] params={int.class,int.class,int.class,Map.class};
        String pieceName=PIECE_PATH+resourceMap.getString(piece);
        for (Spot s : board)
        {
            if(s.getPiece()!=null && s.getPiece().getPieceName().equals("Pawn"))
            {
                int i=s.getCoordinate().getY_pos();
                int j=s.getCoordinate().getX_pos();
                int team=s.getPiece().getTeam();
                s.setPiece((Piece) Class.forName(pieceName).getDeclaredConstructor(params).newInstance(j,i,team,myRules));
            }
        }
    }

    /**
     * Make all the pieces in the board capable of jumping
     */
    public void makePiecesJump()
    {
        for (Spot s : board)
        {
            if(s.getPiece()!=null)
            {

            }
        }
    }

    /**
     * Make all the pieces in the board capable of attacking their own team's pieces
     */
    public void makePiecesCannibalize()
    {
        myRules.put("All|jump","true");
        for (Spot s : board)
        {
            if(s.getPiece()!=null)
            {
                s.getPiece().updateRules(myRules);
            }
        }
    }

    /**
     *
     * @return list of portal spots
     */
    public List<Spot> getPortalList(){
        List<Spot> portals=new ArrayList<>();
        for (Spot s : board)
        {
            if(((GameSpot) s).getTypeOfSpot()==1)
            {
                portals.add(s);
            }
        }
        return portals;
    }

    /**
     *
     * @return list of blackhole spots
     */
    public List<Spot> getBlackHoleList(){
        List<Spot> blackholes=new ArrayList<>();
        for (Spot s : board)
        {
            if(((GameSpot) s).getTypeOfSpot()==2)
            {
                blackholes.add(s);
            }
        }
        return blackholes;
    }


}
