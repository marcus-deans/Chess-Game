package ooga.view.ui;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class InformationPanel extends SharedUIComponents{
  public InformationPanel(){

  }

  public Node createInformationPanel(){
    VBox informationPanel = new VBox();
    informationPanel.setAlignment(Pos.CENTER);

    Text titleText = makeTitleText();
    return informationPanel;
  }

  private Text makeTitleText(){
    Text newTitleText = makeText(getWord("game_title_text"));
    newTitleText.setId("game-title-text");
    return newTitleText;
  }

}
