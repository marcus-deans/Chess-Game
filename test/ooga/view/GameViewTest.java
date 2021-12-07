package ooga.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import ooga.controller.ChessController;
import ooga.util.IncorrectCSVFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class GameViewTest extends DukeApplicationTest {

  private ChessController myController;
  private File myFile;
  private ComboBox myLanguageComboBox;
  private Button myPlayerOneButton;
  private Button mySaveButton;
  private Button myLoadButton;
  private Button myUndoButton;
  private Button myRedoButton;
  private GameView myGameView;


  @BeforeEach
  public void setUpController()
      throws CsvValidationException, IOException, ClassNotFoundException, InvocationTargetException, IncorrectCSVFormatException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    myFile = new File("data/Standard/Standard.sim");
    runAsJFXAction(() -> {
      try {
        myController = new ChessController(8, 8, "Blue", "data/Standard/Standard.sim");
      } catch (IOException e) {
      }
    });
    runAsJFXAction(() -> {
      try {
        myController.initializeFromFile(myFile);
      } catch (IOException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException | IncorrectCSVFormatException | CsvValidationException e) {
        e.printStackTrace();
      }
    });
    myLanguageComboBox = lookup("#language-dropdown").queryComboBox();
    myPlayerOneButton = lookup("#player-1-button").queryButton();
    myLoadButton = lookup("#load-button").queryButton();
    mySaveButton = lookup("#save-button").queryButton();
    myUndoButton = lookup("#undo-button").queryButton();
    myRedoButton = lookup("#redo-button").queryButton();

  }

  @Test
  void testSelectLanguageSpanish(){
    String expected = "Elegir el idioma";
    select(myLanguageComboBox, "Spanish");
    assertEquals(expected, myLanguageComboBox.getPromptText());
  }

  @Test
  void testSelectLanguageFrench(){
    String expected = "Choisir la langue";
    select(myLanguageComboBox, "French");
    assertEquals(expected, myLanguageComboBox.getPromptText());
  }

  @Test
  void testSelectLanguageLatin(){
    String expected = "Dominuc";
    select(myLanguageComboBox, "Latin");
    assertEquals(expected, myLanguageComboBox.getPromptText());
  }

  @Test
  void testSelectLanguageGerman(){
    String unexpected = "English";
    select(myLanguageComboBox, "German");
    assertNotEquals(unexpected, myLanguageComboBox.getPromptText());
  }

  @Test
  void testLoginPlayerOne() {
    String expected = "Player 1 Profile";
    clickOn(myPlayerOneButton);
    assertEquals(expected, myPlayerOneButton.getText());
  }

  @Test
  void testProfilePlayerOne(){
    String expected = "Player 1 Profile";
    clickOn(myPlayerOneButton);
    clickOn(myPlayerOneButton);
    assertEquals(expected, myPlayerOneButton.getText());
  }

  @Test
  void testUndoHistoryButton(){
    String expected = "Undo Move";
    clickOn(undoHistoryButton);
    assertEquals(expected, undoHistoryButton.getText());
  }

  @Test
  void testLoadActionBadFilename () {
    final String BAD_FILE_NAME = "bad_file.sim";
    String expected = "Could not load " + BAD_FILE_NAME;
    runAsJFXAction(() -> {
      try {
        myGameView.loadNewFile(BAD_FILE_NAME);
      } catch (CsvValidationException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (IncorrectCSVFormatException e) {
        e.printStackTrace();
      }
    });
    assertEquals(expected, getDialogMessage());
  }
}
