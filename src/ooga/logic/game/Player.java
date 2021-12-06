package ooga.logic.game;

import ooga.logic.board.Pieces.PieceBundle.Piece;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Player {
    private List<Piece> graveyard;
    private int myIdentifier;
    private String myUsername;
    private String myPassword;
    private int myTeam;
    private String apiURL;

    public Player(int playerIdentifier, String username, String password, int team){
        myIdentifier = playerIdentifier;
        graveyard = new ArrayList<Piece>();
        myUsername = username;
        myPassword = password;
        myTeam = team;
        apiURL = "https://cs307.herokuapp.com";

        System.out.println(apiURL);
    }

    private String readFromProperties(String key){
        try (InputStream input = new FileInputStream("game/Player.properties")) {
            Properties prop = new Properties();

            prop.load(input);

            return prop.getProperty(key);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
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
        String url = "https://cs307.herokuapp.com/createUser?id=" + myUsername + "&password=" + myPassword;

        String result = getFromDatabase(url);

        if(result.equals("createduser")) return 0;
        else if(result.equals("wrongpassword"))return 1;
        else if(result.equals("loggedin"))return 2;

        return -1;
    }

    public void updateUserScore(boolean didWin) throws IOException {
        if(didWin){
            String result = getFromDatabase("https://cs307.herokuapp.com/addScore?id=" + myUsername);
        }else{
            String result = getFromDatabase("https://cs307.herokuapp.com/subtractScore?id=" + myUsername);
        }
    }

    public void setProfileColor() throws IOException {
        URL url = new URL("https://cs307.herokuapp.com/setProfileColor?id=" + myUsername);
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

    public String getUserScore() throws IOException {
        String result = getFromDatabase("https://cs307.herokuapp.com/getUserScore?id=" + myUsername);

        return result;
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
