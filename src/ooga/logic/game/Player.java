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
import java.util.ResourceBundle;

public class Player {
    private List<Piece> graveyard;
    private int myIdentifier;
    private String myUsername;
    private String myPassword;
    private int myTeam;
    private String apiURL;
    private ResourceBundle myResource;


    public Player(int playerIdentifier, String username, String password, int team){
        myIdentifier = playerIdentifier;
        graveyard = new ArrayList<Piece>();
        myUsername = username;
        myPassword = password;
        myTeam = team;
        myResource = ResourceBundle.getBundle("ooga.logic.game.Player");
        apiURL = readFromProperties("api_url");
    }

    private String readFromProperties(String key){
         return myResource.getString(key);
    }


    private String getFromDatabase(String givenURL) throws IOException {
        URL url = new URL(givenURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }

        return result.toString();
    }


    public int checkUser() throws IOException {
        String url = apiURL + readFromProperties("check_user_path") + "?id=" + myUsername + "&password=" + myPassword;

        String result = getFromDatabase(url);

        if(result.equals("createduser")) return 0;
        else if(result.equals("wrongpassword"))return 1;
        else if(result.equals("loggedin"))return 2;

        return -1;
    }

    public void updateUserScore(boolean didWin) throws IOException {
        if(didWin){
            String result = getFromDatabase(apiURL + readFromProperties("add_score_path") +"?id=" + myUsername);
        }else{
            String result = getFromDatabase(apiURL + readFromProperties("subtract_score_path") + "?id=" + myUsername);
        }
    }

    public void setProfileColor() throws IOException {
        String result = getFromDatabase(apiURL + readFromProperties("set_profile_color_path") + "?id=" + myUsername);
    }

    public String getUserScore() throws IOException {
        String result = getFromDatabase(apiURL + readFromProperties("get_user_score_path") + "?id=" + myUsername);

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
