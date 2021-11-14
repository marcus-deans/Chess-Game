package ooga.logic.board;

public class GameSpot implements Spot{
    private Piece piece;
    private Coordinate coordinate;
    private int typeOfSpot;
    private boolean color;
    private boolean empty;

    public GameSpot(Piece piece, int x, int y, int typeOfSpot)
    {
        this.setPiece(piece);
        this.setCoordinate(x,y);
        this.setTypeOfSpot(typeOfSpot);
    }

    @Override
    public void setCoordinate(int x, int y) {
        this.coordinate=new GameCoordinate(x,y);
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public void setPiece(Piece piece) {
        this.piece=piece;
        if (piece==null)
        {
            this.empty=true;
        }
    }

    @Override
    public Piece getPiece() {
        return piece;
    }

    public int getTypeOfSpot() {
        return typeOfSpot;
    }

    public void setTypeOfSpot(int typeOfSpot) {
        this.typeOfSpot = typeOfSpot;
    }

    public void emptySpot()
    {
        this.piece=null;
        this.empty=true;
    }


}
