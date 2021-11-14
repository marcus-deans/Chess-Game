package ooga.logic.board;

public class GameCoordinate implements Coordinate{
    private int x;
    private int y;

    public GameCoordinate(int x, int y)
    {
        this.x=x;
        this.y=y;
    }

    @Override
    public int getX_pos() {
        return x;
    }

    @Override
    public int getY_pos() {
        return y;
    }

    @Override
    public void setCoordinate(int x_pos, int i) {

    }

    @Override
    public void setX_pos(int x_pos) {
        this.x=x_pos;
    }

    @Override
    public void setY_pos(int y_pos) {
        this.y=y_pos;
    }

    @Override
    public void setCoordinate(Coordinate myCoordinate) {

    }
}
