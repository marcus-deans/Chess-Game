package ooga.view.ui;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * JavaFX panel that creates the information panel displaying the title and welcome to the application
 * appropriate resourcebundles being configured, SharedUIComponents, and JavaFX
 *
 * @author marcusdeans
 */
public class InformationPanel extends SharedUIComponents{
  private int myInformationPanelX;
  private int myTitleWidth;
  private VBox myInformationPanel;

  /**
   *
   * @param gridPanelX
   * @param gridDisplayLength
   */
  public InformationPanel(int gridPanelX, int gridDisplayLength){
    myInformationPanelX = gridPanelX+(gridDisplayLength/2);
    myInformationPanel = new VBox();
  }

  /**
   * Create the information panel with all of the content
   * @return the JavaFX information panel
   */
  public Node createInformationPanel(){
    myInformationPanel.setSpacing(getInt("information_panel_spacing"));
    myInformationPanel.setAlignment(Pos.CENTER);

    Text titleText = makeTitleText();
    Text subtitleText = makeSubtitleText();
    myInformationPanel.getChildren().addAll(titleText, subtitleText);

    myInformationPanel.setLayoutX(myInformationPanelX);
    myInformationPanel.setLayoutY(getInt("information_panel_y"));
    return myInformationPanel;
  }

  //compute then ew position for hte information palenl, java cannot compuete it swidth unitl the object has been created
  public void adjustInformationPanelPosition(){
    int newInformationPanelX = (int) (myInformationPanelX-(myInformationPanel.getWidth()/2));
    myInformationPanel.setLayoutX(newInformationPanelX);
  }

  //make JavaFX Text to serve as title
  private Text makeTitleText(){
    Text newTitleText = makeText(getWord("game_title_text"));
    newTitleText.setId("game-title-text");
    return newTitleText;
  }

  //make JavaFX Text to serve as subtitle
  private Text makeSubtitleText(){
    Text newSubtitleText = makeText(getWord("game_subtitle_text"));
    newSubtitleText.setId("game-subtitle-text");
    return newSubtitleText;
  }

}
