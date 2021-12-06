package ooga.logic.board.Pieces.PieceBundle;

import java.util.Map;
import java.util.ResourceBundle;

public class JumpStorage {
  private static final String JUMP = "jump";
  private boolean canJump;

  public JumpStorage(Map<String, String> attributeMap, ResourceBundle pieceProperties, ResourceBundle defaultProperties) {
    setJump(attributeMap,pieceProperties,defaultProperties);
  }


  private void setJump(Map<String, String> attributeMap, ResourceBundle pieceProperties, ResourceBundle defaultProperties) {
    try{
      setCanJump(Boolean.parseBoolean(pieceProperties.getString(JUMP)));
    }
    catch (Exception e){
      setCanJump(Boolean.parseBoolean(defaultProperties.getString(JUMP)));
    }
  }

  private void setCanJump(boolean newJump) {
    canJump = newJump;
  }

  //@Override
  public boolean getValue() {
    return canJump;
  }

}
