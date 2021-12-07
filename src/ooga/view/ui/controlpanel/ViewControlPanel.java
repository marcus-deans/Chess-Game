package ooga.view.ui.controlpanel;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ooga.view.ui.SharedUIComponents;

/**
 * JavaFX panel that creates the view control panel for UI language and appearance Relies on
 * appropriate resourcebundles being configured, SharedUIComponents, and JavaFX
 *
 * @author marcusdeans
 */
public class ViewControlPanel extends SharedUIComponents {
  //General resource file structure
  private static final String VIEW_CONTROL_RESOURCE_FILE_PATH = "ooga.view.viewresources.ViewControlResources";
  private static final ResourceBundle viewControlResources = ResourceBundle.getBundle(
      VIEW_CONTROL_RESOURCE_FILE_PATH);
  private final List<String> viewOptions = Arrays.asList(
      viewControlResources.getString("ViewOptions").split(","));
  //Cosmetic features: languages
  private final List<String> languageTypes = Arrays.asList(
      viewControlResources.getString("LanguageOptions").split(","));
  private ComboBox languagesPrograms;
  private ComboBox viewSetting;

  /**
   * Initialize the view control panel creator
   *
   */
  public ViewControlPanel() {
    createViewControlPanel();
  }

  /**
   * Create the view control panel that allows user to control the UI appearance and language
   * @return the JavaFX HBox that constitutes the view control panel
   */
  public Node createViewControlPanel() {
    VBox myViewControlPanel = new VBox();
    myViewControlPanel.setSpacing(getInt("control_subpanel_spacing"));

    Node viewControlDropdown = initializeViewControlDropdown();
    myViewControlPanel.getChildren().add(viewControlDropdown);

    Node languageControlDropdown = initializeLanguageControlDropdown();
    myViewControlPanel.getChildren().add(languageControlDropdown);

    myViewControlPanel.setId("view-control-panel");

    return myViewControlPanel;
  }

  //create the specific dropdown allowing the user to select which view mode they prefer
  private Node initializeViewControlDropdown() {
    viewSetting = makeComboBox(getWord("view_selection"), viewOptions, (event) -> {
      String myViewOption = viewSetting.getSelectionModel().getSelectedItem().toString();
      if(this.getPanelListener() != null){
        this.getPanelListener().updateColorScheme(viewControlResources.getString(myViewOption));
      }
    });
    viewSetting.setId("view-dropdown");
    return viewSetting;
  }

  //create the dropdown allowing user to select which language they prefer
  private Node initializeLanguageControlDropdown() {
    languagesPrograms = makeComboBox(getWord("language_selection"), languageTypes, (event) -> {
      String lang = (String) languagesPrograms.getValue();
      if(this.getPanelListener() != null){
        this.getPanelListener().updateLanguage(lang);
      }});
    languagesPrograms.setId("language-dropdown");
    return languagesPrograms;
  }
}
