package ooga.view.ui.gameplaypanel;

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
  private final List<String> variantOptions = Arrays.asList(
      variantResources.getString("VariantOptions").split(","));
  private String myDescription;
  private ComboBox variantControlDropdown;
  private Button variantDescriptionButton;

  /**
   * Initialize the information panel creator
   */
  public VariantPanel(String description) {
    myDescription = description;
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

    Node variantControlDropdown = initializeVariantControlDropdown();
    Button variantDescriptionButton = initializeVariantDescriptionButton();

    myVariantPanel.getChildren().addAll(variantControlDropdown, variantDescriptionButton);

    return myVariantPanel;
  }

  //create the specific dropdown allowing the user to select which view mode they prefer
  private Node initializeVariantControlDropdown() {
    variantControlDropdown = makeComboBox(getWord("variant_selection"), variantOptions, (event) -> {
      String myVariantSelection = variantControlDropdown.getSelectionModel().getSelectedItem()
          .toString();
      if (this.getPanelListener() != null) {
        this.getPanelListener().changeVariant(myVariantSelection);
      }
    });
    return variantControlDropdown;
  }

  private Button initializeVariantDescriptionButton() {
    variantDescriptionButton = makeButton(getWord("variant_description_button"), event -> {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle(getWord("variant_description_popup_title"));
      alert.setHeaderText(getWord("variant_description_popup_header"));
      alert.setContentText(myDescription);
      alert.showAndWait();
    });
    variantDescriptionButton.setMaxHeight(getInt("button_height"));
    variantDescriptionButton.setPrefWidth(getInt("button_width"));
    return variantDescriptionButton;
  }

  public void setBoardDescription(String description) {
    variantDescriptionButton.setOnAction(event -> {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle(getWord("variant_description_popup_title"));
      alert.setHeaderText(getWord("variant_description_popup_header"));
      alert.setContentText(myDescription);
      alert.showAndWait();
    });
  }
}
