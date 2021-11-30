package ooga.view.ui;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class InformationPanel extends SharedUIComponents{
  private int myInformationPanelX;
  private int myTitleWidth;
  private VBox myInformationPanel;

  public InformationPanel(int gridPanelX, int gridDisplayLength){
    myInformationPanelX = gridPanelX+(gridDisplayLength/2);
    myInformationPanel = new VBox();
  }

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

  public void adjustInformationPanelPosition(){
    int newInformationPanelX = (int) (myInformationPanelX-(myInformationPanel.getWidth()/2));
    myInformationPanel.setLayoutX(newInformationPanelX);
  }

  private Text makeTitleText(){
    Text newTitleText = makeText(getWord("game_title_text"));
    newTitleText.setId("game-title-text");
    return newTitleText;
  }

  private Text makeSubtitleText(){
    Text newSubtitleText = makeText(getWord("game_subtitle_text"));
    newSubtitleText.setId("game-subtitle-text");
    return newSubtitleText;
  }

}
