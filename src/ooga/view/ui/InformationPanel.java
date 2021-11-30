package ooga.view.ui;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class InformationPanel extends SharedUIComponents{
  private int myInformationPanelX;

  public InformationPanel(int informationPanelX){
    myInformationPanelX = informationPanelX;
  }

  public Node createInformationPanel(){
    VBox informationPanel = new VBox();
    informationPanel.setSpacing(getInt("information_panel_spacing"));
    informationPanel.setAlignment(Pos.CENTER);

    Text titleText = makeTitleText();
    Text subtitleText = makeSubtitleText();
    informationPanel.getChildren().addAll(titleText, subtitleText);

    informationPanel.setLayoutX(myInformationPanelX);
    informationPanel.setLayoutY(getInt("information_panel_y"));
    return informationPanel;
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
