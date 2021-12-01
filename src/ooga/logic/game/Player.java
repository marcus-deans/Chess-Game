package ooga.logic.game;

import ooga.logic.board.Pieces.PieceBundle.Piece;

import java.util.ArrayList;
import java.util.List;

public class Player {

    String myUsername;
    int myTeam;


    List<Piece> graveyard;

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
