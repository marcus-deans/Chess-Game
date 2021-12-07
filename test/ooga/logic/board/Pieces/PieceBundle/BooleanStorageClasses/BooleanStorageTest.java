package ooga.logic.board.Pieces.PieceBundle.BooleanStorageClasses;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BooleanStorageTest {
  private BooleanStorage myStorage;
  private Map<String,String> myRulesMap;
  private ResourceBundle pieceProperties;
  private ResourceBundle defaultProperties;
  private static final String PIECES_PACKAGE = "ooga.logic.board.Pieces.PieceBundle.resources.";

  @BeforeEach
  void setUp() {
      myRulesMap = new HashMap<>();
      myRulesMap.put("canCannibalize", "true");
      myRulesMap.put("isCheckable", "true");

      pieceProperties=ResourceBundle.getBundle(PIECES_PACKAGE+"Bishop");
      defaultProperties= ResourceBundle.getBundle(PIECES_PACKAGE+"Default");

     // myStorage = new cannibalizeStorage(myRulesMap,pieceProperties,defaultProperties);

  }

  @Test
  void cannibalizeCheckFromMap() {
    myStorage = new cannibalizeStorage(myRulesMap,pieceProperties,defaultProperties);
    assertTrue(myStorage.getValue());
    myRulesMap.remove("canCannibalize");
    myRulesMap.put("canCannibalize", "false");
    myStorage.update(myRulesMap);
    assertFalse(myStorage.getValue());
  }

  @Test
  void checkableCheckFromMap() {
    myStorage = new checkableStorage(myRulesMap,pieceProperties,defaultProperties);
    assertTrue(myStorage.getValue());
    myRulesMap.remove("isCheckable");
    myRulesMap.put("isCheckable", "true");
    myStorage.update(myRulesMap);
    assertTrue(myStorage.getValue());
  }

  @Test
  void jumpCheckFromMap() {
    myStorage = new JumpStorage(myRulesMap,pieceProperties,defaultProperties);
    assertFalse(myStorage.getValue());
    myRulesMap.remove("isCheckable");
    myRulesMap.put("isCheckable", "true");
    myStorage.update(myRulesMap);
    assertFalse(myStorage.getValue());
  }

  @Test
  void TeamMattersCheckFromMap() {
    myStorage = new TeamMattersStorage(myRulesMap,pieceProperties,defaultProperties);
    assertFalse(myStorage.getValue());
    myRulesMap.remove("teamMatters");
    myRulesMap.put("teamMatters", "somethingRandom");
    myStorage.update(myRulesMap);
    assertFalse(myStorage.getValue());
  }



}