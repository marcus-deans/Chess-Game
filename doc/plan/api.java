public interface Coordinate{
    /**
     * This method would set the x-position for a coordinate
     */
    public void setX_pos();
    /**
     * This method would set the y-position for a coordinate
     */
    public void setY_pos();
    /**
     * This method would get the x-position for a coordinate
     */
    public double getX_pos();
    /**
     * This method would get the y-position for a coordinate
     */
    public double getY_pos();
}

public interface Piece {
    /**
     * This method would define the movement of the individual piece. For example,
     * for a pawn it would change the y-position of its location by 1
     */
    public void move();

    /**
     * using the definition of the move method for the specific peice
     * and the piece's current position, it would create a list of
     * possible coordinates the piece could move to
     */
    public void updatePossibleMoves();

    /**
     *
     * @return the list of possible coordinates to move to
     */
    public List<Coordinate> getPossibleMoves();

    /**
     * Sets the state of the piece, 0 for empty, 1 for pawn, etc.
     * Needed when a piece is taken or a pawn reaches the end
     */
    public void setState();
    /**
     * Updates the internal coordinate of the piece after it is moved
     */
    public void updatePosition();

    /**
     * Changes the coordinate set for the piece
     */
    public void setCoordinate();

    /**
     * Retrieves the coordinate set for the piece
     */
    public Coordinate getCoordinate();
}

/** General Board/ board change **/
public interface Board {

    /**
     *Create a board given a number of rows and columns
     */
    public void create(int row, int col);

    /**
     * add portals (entrance and exit squares) to the game
     * @param amount of portals to add
     */
    public void makePortals(int amount);

    /**
     * Remove a given amount of portals (default will be all of them)
     * @param amount of portals to remove
     */
    public void removePortals(int amount);

    /**
     * add Black Holes (if touched, piece is lost) to the game
     * @param amount of Black Holes to add
     */
    public void makeBlackHoles();

    /**
     * Remove a given amount of Black Holes (default will be all of them)
     * @param amount of Black Holes to remove
     */
    public void removeBlackHoles();
}

public interface FeatureSelector{
    public void implementFeature(String feature);
}
