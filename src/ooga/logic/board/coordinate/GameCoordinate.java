package ooga.logic.board.coordinate;

public class GameCoordinate implements Coordinate {
    private int x;
    private int y;

    public GameCoordinate(int x, int y)
    {
        this.x=x;
        this.y=y;
    }

    /**
     *
     * @return x position of coordinate
     */
    @Override
    public int getX_pos() {
        return x;
    }

    /**
     *
     * @return y position of coordinate
     */
    @Override
    public int getY_pos() {
        return y;
    }

    /**
     * Sets x and y position of coordinate
     * @param x_pos
     * @param y_pos
     */
    @Override
    public void setCoordinate(int x_pos, int y_pos) {
        setX_pos(x_pos);
        setY_pos(y_pos);
    }


    /**
     * sets x position of coordinate
     * @param x_pos
     */
    @Override
    public void setX_pos(int x_pos) {
        this.x=x_pos;
    }

    /**
     * sets y position of coordinate
     * @param y_pos
     */
    @Override
    public void setY_pos(int y_pos) {
        this.y=y_pos;
    }

    /**
     * sets the x and y positions to those of the given coordinate
     * @param myCoordinate
     */
    @Override
    public void setCoordinate(Coordinate myCoordinate) {
        setCoordinate(myCoordinate.getX_pos(),myCoordinate.getY_pos());
    }


    /**
     * compares the x and y positions of two coordinates
     * @param o
     * @return if their x and y positions are equal, respectively
     */
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameCoordinate coordinate= (GameCoordinate) o;
        return this.x==coordinate.x && this.y==coordinate.y;
    }
}
