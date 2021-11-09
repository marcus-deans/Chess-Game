public interface Coordinates{
    public void setX_pos();
    public void setY_pos();
    public double getX_pos();
    public double getY_pos();
}

public interface Piece {
    public void move();
    public List<Coordinates> getPossibleMoves();
    public void setState();
}

public interface Board {
    public void create();
    public void makePortals();
    public void removePortals();
    public void makeBlackHoles();
    public void removeBlackHoles();
}

public interface FeatureSelector{
    public void implementFeature(String feature);
}