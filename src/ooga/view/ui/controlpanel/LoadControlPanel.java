package ooga.view.ui.controlpanel;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.opencsv.exceptions.CsvValidationException;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import ooga.util.IncorrectCSVFormatException;
import ooga.view.ui.SharedUIComponents;

/**
 * JavaFX panel that creates the load control panel that allows the user to control saving/loading
 * Relies on appropriate resourcebundles being configured, SharedUIComponents, and JavaFX
 *
 * @author marcusdeans, drewpeterson
 */
public class LoadControlPanel extends SharedUIComponents {
  private Timeline myAnimation;

  /**
   *
   * @param animation
   */
  public LoadControlPanel(Timeline animation){
    myAnimation = animation;
    createLoadControlPanel();
  }

  /**
   * Create the load control panel that allows the user to select the file to save/load to/from
   * @return the JavaFX HBox that constitutes the load control panel
   */
  public Node createLoadControlPanel(){
    VBox panel = new VBox();
    panel.setSpacing(getInt("control_subpanel_spacing"));

    Node loadFileButton = initializeLoadFileButton();
    panel.getChildren().add(loadFileButton);

    Node saveFileButton = initializeSaveFileButton();
    panel.getChildren().add(saveFileButton);

//    panel.setLayoutX(myControlPanelX);
//    panel.setLayoutY(getInt("load_control_panel_y"));
    panel.setId("load-control-panel");

    return panel;
  }

  //create button to load file from source
  private Node initializeLoadFileButton() {
    Button loadFileButton = makeButton(getWord("load_text"), event -> {
      File selectedCSVFile = makeFileChooser("SIM files (*.sim)", "*.sim");
      if(selectedCSVFile != null && this.getPanelListener() != null) {
        String filename = selectedCSVFile.getAbsolutePath();
        try {
          this.getPanelListener().loadNewFile(filename);
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
      }
    });
    return loadFileButton;
  }

  //create FileChooser that will allow the user to select the file they prefer
  private File makeFileChooser(String description, String extensions) {
    FileChooser myFileChooser = new FileChooser();
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(description, extensions);
    myFileChooser.getExtensionFilters().add(extFilter);
    File selectedFile = myFileChooser.showOpenDialog(null);
    return selectedFile;
  }


  //TODO: this one works in OOLALA, fix to work here
  //create button to save current grid to file
  private Node initializeSaveFileButton() {
    Button saveFileButton = makeButton(getWord("save_text"), event -> {
      if(this.getPanelListener() != null) {
        this.getPanelListener().saveCurrentFile();
      }
    });
    return saveFileButton;
  }

}
