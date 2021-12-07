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
  private ComboBox myViewComboBox;
  private ComboBox myCheatCodeBox;
  private Button myPlayerOneButton;
  private Button mySaveButton;
  private Button myLoadButton;
  private Button myUndoButton;
  private Button myRedoButton;
  private Button myResetButton;
  private Button myDescriptionButton;
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
    myViewComboBox = lookup("#view-dropdown").queryComboBox();
    myCheatCodeBox = lookup("#cheatcontrol-dropdown").queryComboBox();
    myPlayerOneButton = lookup("#player-1-button").queryButton();
    myLoadButton = lookup("#load-button").queryButton();
    mySaveButton = lookup("#save-button").queryButton();
    myUndoButton = lookup("#undo-button").queryButton();
    myRedoButton = lookup("#redo-button").queryButton();
    myResetButton = lookup("#reset-button").queryButton();
    myDescriptionButton = lookup("#description-button").queryButton();
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
  void testSelectViewSpace(){
    String expected = "Space";
    select(myViewComboBox, "Space");
    assertEquals(expected, myViewComboBox.getSelectionModel().getSelectedItem().toString());
  }


  @Test
  void testSelectViewDark(){
    String expected = "Dark";
    select(myViewComboBox, "Dark");
    assertEquals(expected, myViewComboBox.getSelectionModel().getSelectedItem().toString());
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
    String expected = "Undo";
    clickOn(myUndoButton);
    assertEquals(expected, myUndoButton.getText());
  }

  @Test
  void testSelectLanguageGerman(){
    String unexpected = "English";
    select(myLanguageComboBox, "German");
    assertNotEquals(unexpected, myLanguageComboBox.getPromptText());
  }

  @Test
  void testDescriptionButtonPopupRandomMessage(){
    String unexpected = "Description";
    clickOn(myDescriptionButton);
    assertNotEquals(unexpected, getDialogMessage());
  }

  @Test
  void testRedoHistoryButton(){
    String expected = "Redo";
    clickOn(myRedoButton);
    assertEquals(expected, myRedoButton.getText());
  }

  @Test
  void testSelectViewUNC(){
    String expected = "UNC";
    select(myViewComboBox, "UNC");
    assertEquals(expected, myViewComboBox.getSelectionModel().getSelectedItem().toString());
  }

  @Test
  void testDescriptionButtonPopup(){
    String expected = "Good Ol'fashioned Game of Chess";
    clickOn(myDescriptionButton);
    assertEquals(expected, getDialogMessage());
  }

  @Test
  void testResetButton(){
    String expected = "Reset";
    clickOn(myResetButton);
    assertEquals(expected, myResetButton.getText());
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

  @Test
  void testDescriptionClickButton(){
    String expected = "Description";
    clickOn(myDescriptionButton);
    assertEquals(expected, myDescriptionButton.getText());
  }

  @Test
  void testSelectViewDukeRoyal(){
    String expected = "Duke-Royal";
    select(myViewComboBox, "Duke-Royal");
    assertEquals(expected, myViewComboBox.getSelectionModel().getSelectedItem().toString());
  }
}
