package ooga.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
    String expected = "";
  }

  @Test

}
