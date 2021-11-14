package ooga.logic.board.coordinate;

public class GameCoordinate implements Coordinate {
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
    public void setX_pos(int x_pos) {
        this.x=x_pos;
    }

    @Override
    public void setY_pos(int y_pos) {
        this.y=y_pos;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameCoordinate coordinate= (GameCoordinate) o;
        return this.x==coordinate.x && this.y==coordinate.y;
    }
}
