package ooga.logic.game;

import ooga.logic.board.Pieces.PieceBundle.Piece;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Piece> graveyard;
    private String username;
    private String password;

    public Player(){
        graveyard = new ArrayList<Piece>();
    }

    public void addPieceToGraveyard(Piece piece){
        graveyard.add(piece);
    }

    public List<Piece> getGraveyard(){
        return graveyard;
    }


}
