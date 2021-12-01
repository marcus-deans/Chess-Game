package ooga.logic.game;

import ooga.logic.board.Pieces.PieceBundle.Piece;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Piece> graveyard;
    private String myUsername;
    private int myTeam;

    public Player(String username, int team){
        graveyard = new ArrayList<Piece>();
        myUsername = username;
        myTeam = team;
    }

    public void setUsername(String username) {
        this.myUsername = username;
    }

    public String getUsername() {
        return myUsername;
    }

    public int getTeam(){
        return myTeam;
    }

    public void addPieceToGraveyard(Piece piece){
        graveyard.add(piece);
    }

    public List<Piece> getGraveyard(){
        return graveyard;
    }

}
