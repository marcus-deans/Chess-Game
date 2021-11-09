package ooga.logic.board;

import java.util.List;

public interface Piece {


    //returns all available tiles that the piece can move to
    public List<Coordinate> getAvailableTiles();


}
