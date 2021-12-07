package ooga.logic.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ResourceBundle;

public class PlayerTest {
    private ResourceBundle myResource = ResourceBundle.getBundle("ooga.logic.game.REST");

    @Test
    public void initializeNewPlayer() throws IOException {
        //pick a random number from 0 to 10000 to get a "random" username that is very highly likely unique and haven't been created before
        String uniqueUsername = String.valueOf((int) (Math.random()*10000)) + "PlayerTest";
        Player player1 = new Player(1, uniqueUsername, "password", 1);
        boolean result = player1.checkUser();
        Assertions.assertEquals(true, result);
    }

    @Test
    public void initializeExistingPlayerCorrectPassword() throws IOException {
        //pick a random number from 0 to 10000 to get a "random" username that is very highly likely unique and haven't been created before
        String uniqueUsername = String.valueOf((int) (Math.random()*10000)) + "PlayerTest";
        Player player1 = new Player(1, uniqueUsername, "password", 1);
        player1.checkUser();

        //create a 2nd player with the same username w/ the same password
        Player player2 = new Player(1, uniqueUsername, "password", 1);
        boolean result = player2.checkUser();
        Assertions.assertEquals(result, result);
    }

    @Test
    public void initializeExistingPlayerWrongPassword() throws IOException {
        //pick a random number from 0 to 10000 to get a "random" username that is very highly likely unique and haven't been created before
        String uniqueUsername = String.valueOf((int) (Math.random()*10000)) + "PlayerTest";
        Player player1 = new Player(1, uniqueUsername, "password", 1);
        player1.checkUser();

        //create a 2nd player with the same username with a different/wrong password
        Player player2 = new Player(1, uniqueUsername, "wrongpassword", 1);
        boolean result = player2.checkUser();
        Assertions.assertEquals(false, result);
    }

    @Test
    public void updateWinScoreTest() throws IOException {
        //pick a random number from 0 to 10000 to get a "random" username that is very highly likely unique and haven't been created before
        String uniqueUsername = String.valueOf((int) (Math.random()*10000)) + "PlayerTest";
        Player player1 = new Player(1, uniqueUsername, "password", 1);
        player1.checkUser();

        player1.updateUserScore(true);
        String result = player1.getUserScore();

        Assertions.assertEquals("1, 0", result);
    }

    @Test
    public void updateLossScoreTest() throws IOException {
        //pick a random number from 0 to 10000 to get a "random" username that is very highly likely unique and haven't been created before
        String uniqueUsername = String.valueOf((int) (Math.random()*10000)) + "PlayerTest";
        Player player1 = new Player(1, uniqueUsername, "password", 1);
        player1.checkUser();

        //false signifies that the player lost the game
        player1.updateUserScore(false);
        String result = player1.getUserScore();

        Assertions.assertEquals("0, 1", result);
    }

    @Test
    public void setProfileColorTest() throws IOException {
        //pick a random number from 0 to 10000 to get a "random" username that is very highly likely unique and haven't been created before
        String uniqueUsername = String.valueOf((int) (Math.random()*10000)) + "PlayerTest";
        Player player1 = new Player(1, uniqueUsername, "password", 1);
        player1.checkUser();

        //false signifies that the player lost the game
        player1.setProfileColor("red");
        String result = player1.getProfileColor();

        Assertions.assertEquals("red", result);
    }

    @Test
    public void getTeamTest() throws IOException {
        Player player1 = new Player(1, "username", "password", 1);
        int result = player1.getTeam();

        Assertions.assertEquals(1, result);
    }

    @Test
    public void getUsernameTest() throws IOException {
        Player player1 = new Player(1, "username", "password", 1);
        String result = player1.getUsername();

        Assertions.assertEquals("username", result);
    }

}
