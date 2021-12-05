package ooga.logic.game;

import ooga.logic.board.Pieces.PieceBundle.Piece;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Piece> graveyard;
    private String myUsername;
    private String myPassword;
    private int myTeam;

    public Player(String username, String password, int team) throws IOException {
        graveyard = new ArrayList<Piece>();
        myUsername = username;
        myPassword = password;
        myTeam = team;

        URL url = new URL("http://localhost:3001/createUser?id=" + username + "&password=" + password);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        StringBuilder result = new StringBuilder();
        connection.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
    }

    public void updateUserScore(boolean didWin) throws IOException {
        if(didWin){
            URL url = new URL("http://localhost:3001/addScore?id=" + myUsername);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            StringBuilder result = new StringBuilder();
            connection.setRequestMethod("GET");
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    result.append(line);
                }
            }
        }else{
            URL url = new URL("http://localhost:3001/subtractScore?id=" + myUsername);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            StringBuilder result = new StringBuilder();
            connection.setRequestMethod("GET");
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    result.append(line);
                }
            }
        }
    }

    public int getUserData() throws IOException {
        URL url = new URL("http://localhost:3001/getUserScore?id=" + myUsername);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        StringBuilder result = new StringBuilder();
        connection.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }

        return Integer.parseInt(result.toString());
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
