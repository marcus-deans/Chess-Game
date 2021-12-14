package ooga.logic.game;

import ooga.logic.board.Pieces.PieceBundle.Piece;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    /**
     * method for reading from the properties file given a key
     * @param key
     */
    private String readFromProperties(String key){
         return myResource.getString(key);
    }

    /**
     * calls get request to the given URL. This is how we recieve and send data to the database.
     * @param givenURL
     */
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

    /**
     * This method checks whether the user has successfully signed in or not. If not, they need to re-enter their
     * password (returns false)
     */
    public boolean checkUser() throws IOException {
        String url = apiURL + readFromProperties("check_user_path") + "?" + readFromProperties("id_parameter") + myUsername + "&" + readFromProperties("password_parameter") + myPassword;

        String result = getFromDatabase(url);

        myLogger.log(Level.INFO, result);

        if(result.equals(readFromProperties("insert_new_user_output")) || result.equals(readFromProperties("successful_login_output"))) return true;
        else if(result.equals(readFromProperties("incorrect_password_match_output"))) return false;

        return false;
    }

    /**
     * This method updates the win and the loss count for each user in the database. This means even when you close
     * the program fully locally, it is still saved in the cloud and can retrieve it.
     *
     * @param didWin
     */
    public String updateUserScore(boolean didWin) throws IOException {
        String result;
        if(didWin){
            result = getFromDatabase(apiURL + readFromProperties("add_score_path") +"?" + readFromProperties("id_parameter") + myUsername);
        }else{
            result = getFromDatabase(apiURL + readFromProperties("subtract_score_path") + "?" + readFromProperties("id_parameter") + myUsername);
        }

        myLogger.log(Level.INFO, result);

        return result;
    }

    /**
     * This method sets profile color and saves it in the database for this player
     *
     * @param color
     */
    public String setProfileColor(String color) throws IOException {
        String result = getFromDatabase(apiURL + readFromProperties("set_profile_color_path") + "?" + readFromProperties("id_parameter") + myUsername+ "&color=" + color);

        myLogger.log(Level.INFO, result);

        return result;
    }

    /**
     * This method gets the profile color of this player from the database
     *
     * @return String
     */
    public String getProfileColor() throws IOException {
        String result = getFromDatabase(apiURL + readFromProperties("get_profile_color_path") + "?" + readFromProperties("id_parameter") + myUsername);

        return result;
    }

    /**
     * This method gets the score of this player from the database. returns both wincount and losscount.
     *
     * @return String
     */
    public String getUserScore() throws IOException {
        String result = getFromDatabase(apiURL + readFromProperties("get_user_score_path") + "?" + readFromProperties("id_parameter") + myUsername);

        return result;
    }

    /**
     * returns the username of this player.
     *
     * @return String
     */
    public String getUsername() {
        return myUsername;
    }

    /**
     * returns the team of this player.
     *
     * @return int
     */
    public int getTeam(){
        return myTeam;
    }

    /**
     * adds piece to the player's graveyard if their piece was captured.
     *
     * @param piece
     */
    public void addPieceToGraveyard(Piece piece){
        graveyard.add(piece);
    }

    /**
     * returns player's graveyard.
     */
    public List<Piece> getGraveyard(){
        return graveyard;
    }
}
