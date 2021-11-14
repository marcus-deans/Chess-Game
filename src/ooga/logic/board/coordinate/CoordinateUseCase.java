package ooga.logic.board.coordinate;

public class CoordinateUseCase implements Coordinate {
    private int x_pos;
    private int y_pos;

    public CoordinateUseCase(){
        this(0,0);
    }
    public CoordinateUseCase(int x,int y)
    {
        setX_pos(x);
        setY_pos(y);
    }
    /**
     * This method would set the x-position for a coordinate
     */
    public void setX_pos(int x_pos){
        this.x_pos=x_pos;
    }
    /**
     * This method would set the y-position for a coordinate
     */
    public void setY_pos(int y_pos){
        this.y_pos=y_pos;
    }

    @Override
    public void setCoordinate(Coordinate myCoordinate) {
        setX_pos(myCoordinate.getX_pos());
        setY_pos(myCoordinate.getY_pos());
    }

    public void setCoordinate(int xPos, int yPos){
        setX_pos(xPos);
        setY_pos(yPos);
    }

    /**
     * This method would get the x-position for a coordinate
     */
    public int getX_pos()
    {
        return x_pos;
    }
    /**
     * This method would get the y-position for a coordinate
     */
    public int getY_pos()
    {
        return y_pos;
    }
}
