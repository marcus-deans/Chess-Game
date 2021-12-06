package ooga.logic.game;

import ooga.logic.board.Pieces.PieceBundle.Piece;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Piece> graveyard;
    private String myUsername;
    private String myPassword;
    private int myTeam;
    private int userState;

    public Player(String username, String password, int team){
        graveyard = new ArrayList<Piece>();
        myUsername = username;
        myPassword = password;
        myTeam = team;
    }

    private String getFromDatabase(String givenURL) throws IOException {
        URL url = new URL(givenURL);
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

        return result.toString();
    }


    public int checkUser() throws IOException {
        String url = "http://localhost:3001/createUser?id=" + myUsername + "&password=" + myPassword;

        String result = getFromDatabase(url);

        if(result.toString().equals("createduser")) return 0;
        else if(result.toString().equals("wrongpassword"))return 1;
        else if(result.toString().equals("loggedin"))return 2;

        return -1;
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

    public int doesUserExist(){
        return userState;
    }

    public void setProfileColor() throws IOException {
        URL url = new URL("http://localhost:3001/setProfileColor?id=" + myUsername);
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
