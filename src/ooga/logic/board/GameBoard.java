package ooga.logic.board;

import java.util.ArrayList;
import java.util.List;

public class GameBoard implements Board{
    List<Spot> board;
    private int rows;
    private int columns;
    private Spot[][] spotArr;


    public GameBoard(int rows, int columns)
    {
        board=new ArrayList<>();
        this.rows=rows;
        this.columns=columns;
        spotArr=new Spot[rows][columns];
    }

    @Override
    public void setupBoard(String[][] setup) {
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<columns;j++)
            {
                if((i+j)%2==0)
                {
                    spotArr[i][j]=new GameSpot(new Piece(setup[i][j]),j,i,0,false);
                    board.add(new GameSpot(new Piece(setup[i][j]),j,i,0,false));
                }
                else{
                    board.add(new GameSpot(new Piece(setup[i][j]),j,i,0,true));
                    spotArr[i][j]=new GameSpot(new Piece(setup[i][j]),j,i,0,true);
                }

            }
        }
    }

    @Override
    public List<Spot> getFullBoard()
    {
        return board;
    }

    public void updateBoard()
    {

    }


}
