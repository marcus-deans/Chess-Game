package ooga.logic.board.coordinate;

public interface Coordinate{
    /**
     * This method would set the x-position for a coordinate
     */
    public void setX_pos(int x_pos);
    /**
     * This method would set the y-position for a coordinate
     */
    public void setY_pos(int y_pos);

    public void setCoordinate(Coordinate myCoordinate);
    /**
     * This method would get the x-position for a coordinate
     */
    public int getX_pos();
    /**
     * This method would get the y-position for a coordinate
     */
    public int getY_pos();

    public void setCoordinate(int x_pos, int y_pos);
}
