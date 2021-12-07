package ooga.view.ui.gameplaypanel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import ooga.view.ui.SharedUIComponents;

/**
 * JavaFX panel that creates the information panel display the game type, author, and name. Relies
 * on appropriate resourcebundles being configured, SharedUIComponents, and JavaFX
 *
 * @author marcusdeans, drewpeterson
 */
public class VariantPanel extends SharedUIComponents {

  private static final String VARIANT_RESOURCE_FILE_PATH = "ooga.view.viewresources.VariantResources";
  private static final ResourceBundle variantResources = ResourceBundle.getBundle(
      VARIANT_RESOURCE_FILE_PATH);
  private List<String> cheatCodeOptions;
  private String myDescription;
  private ComboBox cheatControlDropDown;
  private Button variantDescriptionButton;

  /**
   * Initialize the information panel creator
   */
  public VariantPanel(String description) {
    myDescription = description;
    initializeDropdownOptions();
  }

  //create the list of cheat code options that can be selected from
  private void initializeDropdownOptions(){
    cheatCodeOptions = new ArrayList<String>();
    String[] cheatCodeIdentifiers = getString("CheatCodeOptions").split(",");
    for(String identifier : cheatCodeIdentifiers){
      String codeName = getString(identifier);
      cheatCodeOptions.add(String.format("Alt+%s: %s", identifier, codeName));
    }
  }

  /**
   * Create the information panel that displays type, name, and author of the simulation on-screen
   *
   * @return the JavaFX HBox that constitutes the information panel
   */
  public Node createVariantPanel() {
    VBox myVariantPanel = new VBox();
    myVariantPanel.setSpacing(getInt("gameplay_subpanel_spacing"));
    myVariantPanel.setId("variant-panel");

    Node variantControlDropdown = initializeCheatControlDropdown();
    Button variantDescriptionButton = initializeVariantDescriptionButton();

    myVariantPanel.getChildren().addAll(variantControlDropdown, variantDescriptionButton);

    return myVariantPanel;
  }

  //create the specific dropdown allowing the user to select which view mode they prefer
  private Node initializeCheatControlDropdown() {
    cheatControlDropDown = makeComboBox(getWord("cheat_code_selection"), cheatCodeOptions, (event) -> {
      String myCheatCodeSelection = cheatControlDropDown.getSelectionModel().getSelectedItem()
          .toString();
      if (this.getPanelListener() != null) {
        this.getPanelListener().selectCheatCode(myCheatCodeSelection.split(" ")[1]);
      }
    });
    cheatControlDropDown.setId("cheatcontrol-dropdown");
    return cheatControlDropDown;
  }

  //create the JavaFX button that will show a description of hte simulation
  private Button initializeVariantDescriptionButton() {
    variantDescriptionButton = makeButton(getWord("variant_description_button"), event -> {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle(getWord("variant_description_popup_title"));
      alert.setHeaderText(getWord("variant_description_popup_header"));
      alert.setContentText(myDescription);
      alert.showAndWait();
    });
    variantDescriptionButton.setId("description-button");
    variantDescriptionButton.setMaxHeight(getInt("button_height"));
    variantDescriptionButton.setPrefWidth(getInt("button_width"));
    return variantDescriptionButton;
  }

  /**
   * Set the new description of the simulation
   * @param description the text of the simulation description
   */
  public void setBoardDescription(String description) {
    myDescription = description;
    variantDescriptionButton.setOnAction(event -> {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle(getWord("variant_description_popup_title"));
      alert.setHeaderText(getWord("variant_description_popup_header"));
      alert.setContentText(myDescription);
      alert.showAndWait();
    });
  }

  //retrieves relevant word from the "words" ResourceBundle
  private String getString(String key) {
    String value;
    try {
      value = variantResources.getString(key);
    } catch (Exception exception) {
      value = "error";
    }
    return value;
  }
}
