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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Player {
    private List<Piece> graveyard;
    private int myIdentifier;
    private String myUsername;
    private String myPassword;
    private int myTeam;
    private String apiURL;
    private ResourceBundle myResource;
    private Logger myLogger;

    public Player(int playerIdentifier, String username, String password, int team){
        myIdentifier = playerIdentifier;
        graveyard = new ArrayList<Piece>();
        myUsername = username;
        myPassword = password;
        myTeam = team;
        myResource = ResourceBundle.getBundle("ooga.logic.game.REST");
        apiURL = readFromProperties("api_url");
        myLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
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


    public boolean checkUser() throws IOException {
        String url = apiURL + readFromProperties("check_user_path") + "?" + readFromProperties("id_parameter") + myUsername + "&" + readFromProperties("password_parameter") + myPassword;

        String result = getFromDatabase(url);

        if(result.equals(readFromProperties("insert_new_user_output")) || result.equals(readFromProperties("successful_login_output"))) return true;
        else if(result.equals(readFromProperties("incorrect_password_match_output"))) return false;

        return false;
    }

    public void updateUserScore(boolean didWin) throws IOException {
        String result;
        if(didWin){
            result = getFromDatabase(apiURL + readFromProperties("add_score_path") +"?" + readFromProperties("id_parameter") + myUsername);
        }else{
            result = getFromDatabase(apiURL + readFromProperties("subtract_score_path") + "?" + readFromProperties("id_parameter") + myUsername);
        }

        myLogger.log(Level.INFO, result);
    }

    public void setProfileColor(String color) throws IOException {
        String result = getFromDatabase(apiURL + readFromProperties("set_profile_color_path") + "?" + readFromProperties("id_parameter") + myUsername+ "&color=" + color);

        myLogger.log(Level.INFO, result);
    }

    public String getProfileColor() throws IOException {
        String result = getFromDatabase(apiURL + readFromProperties("get_profile_color_path") + "?" + readFromProperties("id_parameter") + myUsername);

        return result;
    }

    public String getUserScore() throws IOException {
        String result = getFromDatabase(apiURL + readFromProperties("get_user_score_path") + "?" + readFromProperties("id_parameter") + myUsername);

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
