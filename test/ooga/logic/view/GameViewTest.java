//package ooga.logic.view;
//
//import java.io.File;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.paint.Color;
//import javafx.scene.paint.Paint;
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;
//import org.junit.jupiter.api.Test;
//import util.DukeApplicationTest;
//
//public class ViewTest {
//
//}
//
//package cellsociety.view;
//
//    import static org.junit.jupiter.api.Assertions.*;
//
//    import javafx.scene.control.Button;
//    import javafx.scene.control.ComboBox;
//    import javafx.scene.control.Labeled;
//    import javafx.scene.control.TextInputControl;
//    import javafx.scene.input.KeyCode;
//    import javafx.scene.paint.Color;
//    import javafx.scene.paint.Paint;
//    import javafx.stage.FileChooser;
//    import javafx.stage.Stage;
//    import org.junit.jupiter.api.BeforeAll;
//    import org.junit.jupiter.api.Test;
//    import org.junit.jupiter.api.TestInstance;
//    import util.DukeApplicationTest;
//
//    import java.io.File;
//
//class GameViewTest extends DukeApplicationTest {
//  // keep only if needed to call application methods in tests
//  private GameView myGameView;
//
//  public static final int FRAME_WIDTH = 733;
//  public static final int FRAME_HEIGHT = 680;
//  public static final Paint BACKGROUND = Color.web("#00539B");
//
//  private String filename;
//
//  private void selectFile(){
//    File selectedSIMFile = makeFileChooser("SIM files (*.sim)", "*.sim");
//    filename = selectedSIMFile.getAbsolutePath();
//    System.out.println(filename);
//  }
//
//  //this method is run BEFORE EACH test to set up application in a fresh state
//  @Override
//  public void start (Stage stage) {
//    selectFile();
//    myGameView = new GameView(FRAME_WIDTH, FRAME_HEIGHT, BACKGROUND, filename);
//    myGameView.start(stage);
//  }
//
//  private File makeFileChooser(String description, String extensions) {
//    FileChooser myFileChooser = new FileChooser();
//    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(description, extensions);
//    myFileChooser.getExtensionFilters().add(extFilter);
//    File selectedFile = myFileChooser.showOpenDialog(null);
//    return selectedFile;
//  }
//
//  @Test
//  void testLoadButton(){
//    Button button = lookup("Load").query();
//    //String expected = "CLICK test!";
//    clickOn(button);
//  }
//
//  @Test
//  void testSaveButton(){
//    Button button = lookup("Save").query();
//    clickOn(button);
//  }
//
//  @Test
//  void testRunButton(){
//    Button button = lookup("Run Game").query();
//    clickOn(button);
//  }
//
//  @Test
//  void testPauseAndResumeButton(){
//    Button button = lookup("Pause Game").query();
//    clickOn(button);
//
//    Button button2 = lookup("Resume Game").query();
//    clickOn(button2);
//  }
//
//  @Test
//  void testIncrementButton(){
//    Button button = lookup("Increment").query();
//    clickOn(button);
//  }
//
//  @Test
//  void testSpeedButtons(){
//    Button button = lookup("Speed -").query();
//    clickOn(button);
//    Button button2 = lookup("Speed +").query();
//    clickOn(button2);
//  }
//
//  @Test
//  void testDescriptionButton(){
//    Button button = lookup("Description").query();
//    clickOn(button);
//  }
//
//  @Test
//  void testChooseView(){
//    ComboBox box = lookup("Choose View").queryComboBox();
//    select(box, "UNC");
//  }
//
//  @Test
//  void testChooseLanguage(){
//    ComboBox box = lookup("Choose Language").queryComboBox();
//    select(box, "Spanish");
//  }
//
//  /*
//  // tests for different kinds of UI components
//  @Test
//  void testTextFieldAction () {
//    String expected = "ENTER test!";
//    // GIVEN, app first starts up
//    // WHEN, text is typed and action is activated with ENTER key
//    clickOn(myTextField).write(expected).write(KeyCode.ENTER.getChar());
//    // THEN, check label text has been updated to match input
//    assertLabelText(expected);
//  }
//
//  @Test
//  void testLoadActionBadFilename () {
//    final String BAD_FILE_NAME = "bad_file.txt";
//    String expected = "Could not load " + BAD_FILE_NAME;
//    // GIVEN, app first starts up
//    // WHEN, bad file name is entered to create an error dialog box
//    // Note, RUN needed because new JFX component (DialogBox) is created by loadData call
////    runAsJFXAction(() -> myLifeView.load(BAD_FILE_NAME));
//    // THEN, check proper error is displayed
//    assertEquals(expected, getDialogMessage());
//  }
//
//  // everything tests text of the label
//  private void assertLabelText (String expected) {
//    assertEquals(expected, myLabel.getText());
//  }
//  */
//}