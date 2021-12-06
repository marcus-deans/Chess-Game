package ooga.logic.board.Pieces.PieceBundle.VariableStorageClasses;

import java.util.Map;
import java.util.ResourceBundle;

public class TeamMattersStorage extends BooleanStorage {
  private static final String TEAM_MATTERS = "teamMatters";
  private boolean teamMatters;

  public TeamMattersStorage(Map<String, String> attributeMap, ResourceBundle pieceProperties, ResourceBundle defaultProperties) {
    setMyTeamMatters(attributeMap,pieceProperties,defaultProperties);
  }

  private void setMyTeamMatters(Map<String, String> attributeMap, ResourceBundle pieceProperties, ResourceBundle defaultProperties) {
    try{
      if (attributeMap.containsKey(TEAM_MATTERS)){
        setTeamMatters(Boolean.parseBoolean(attributeMap.get(TEAM_MATTERS)));
      }
      else{
        setTeamMatters(Boolean.parseBoolean(pieceProperties.getString(TEAM_MATTERS)));
      }
    }
    catch (Exception e){
      setTeamMatters(Boolean.parseBoolean(defaultProperties.getString(TEAM_MATTERS)));
    }
  }

  private void setTeamMatters(boolean value){
    teamMatters = value;
  }

  @Override
  public boolean getValue() {
    return teamMatters;
  }
}
