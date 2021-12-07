package ooga.logic.board.Pieces.PieceBundle.SpotCollectionStorageClasses;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.ResourceBundle;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;


abstract public class SpotCollectionStorage implements SpotCollectionStorageInterface {
  private static final String CAPTURE = "capture";
  private static final String MOVEMENT = "movement";
  private static final String WIDTH = "width";
  private static final String HEIGHT = "height";

  private SpotCollection mySpotCollection;
  private static final String SPOT_COLLECTION_BASE = SpotCollection.class.getPackageName();
  private static final int DEFAULT_BOARD_WIDTH = 8;
  private static final int DEFAULT_BOARD_HEIGHT = 8;


  private ResourceBundle pieceProperties;
  private ResourceBundle defaultProperties;
  private String pieceToString;
  private String teamMatters;
  private Map<String,String> myDataMap;

  /**
   * 
   * @param pieceToString
   * @param attributeMap
   * @param pieceProperties
   * @param defaultProperties
   * @param teamMatters
   */
  public SpotCollectionStorage(String pieceToString, Map<String, String> attributeMap,
      ResourceBundle pieceProperties, ResourceBundle defaultProperties, String teamMatters) {
      this.pieceProperties = pieceProperties;
      this.defaultProperties = defaultProperties;
      this.pieceToString = pieceToString;
      this.teamMatters = teamMatters;
      this.myDataMap = attributeMap;
      setBundle();
  }

  private void setBundle() {
    try{
      if (myDataMap.containsKey(getMySpotType())){
        setMySpotCollection(
            getFromSpotCollection()
        );
      }
      else{
        setMySpotCollection(
            getSpotCollectionFromBundle(pieceProperties)
        );
      }
    }
    catch (Exception e){
      setDefaultBundle();
    }
  }

  private void setDefaultBundle() {
    try{
      setMySpotCollection(
          getFromType(getMySpotType())
      );

    }
    catch(Exception e){
      movementOrNone();
    }
  }
  private void movementOrNone() {
    try{
      setMySpotCollection(
          getFromType(MOVEMENT)
      );

    }
    catch(Exception e){
      getDefaultState();
    }
  }

  protected void getDefaultState(){
  try {
    setMySpotCollection(
        getSpotCollectionFromBundle(defaultProperties)
    );
  }
  catch (Exception e){
    //SOME KIND OF ERROR
  }
  }

  private SpotCollection getSpotCollectionFromBundle(ResourceBundle pieceProperties)
      throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
    Class[] params={int.class};
    return (SpotCollection) Class.forName(
        String.format("%s.%s", SPOT_COLLECTION_BASE, pieceProperties.
            getString(
                String.format("%s%s", getMySpotType(), teamMatters)
            ))
    ).getConstructor(params).newInstance(Math.max(getBoardHeight(),getBoardWidth()));
  }

  private int getBoardWidth(){
    if (myDataMap == null){
      return DEFAULT_BOARD_WIDTH;
    }
    return myDataMap.containsKey(WIDTH) ? Integer.parseInt(myDataMap.get(WIDTH)) : DEFAULT_BOARD_WIDTH;
  }
  private int getBoardHeight(){
    if (myDataMap == null){
      return DEFAULT_BOARD_HEIGHT;
    }
    return myDataMap.containsKey(HEIGHT) ? Integer.parseInt(myDataMap.get(HEIGHT)) : DEFAULT_BOARD_HEIGHT;

  }

  private SpotCollection getFromSpotCollection()
      throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
    Class[] params={int.class};
    return (SpotCollection) Class.forName(
        String.format("%s.%s",SPOT_COLLECTION_BASE ,
            myDataMap.get(getMySpotType())
        )).getConstructor(params).newInstance(Math.max(getBoardHeight(),getBoardWidth()));
  }

  private SpotCollection getFromType(String mySpotType)
      throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
    Class[] params={int.class};
    return (SpotCollection) Class.forName(
        String.format("%s.%s%s", SPOT_COLLECTION_BASE, pieceToString, capitalizeFirst(mySpotType))
    ).getConstructor(params).newInstance(Math.max(getBoardHeight(),getBoardWidth()));
  }


  private void setMySpotCollection(SpotCollection myNewSpotCollection){
    mySpotCollection = myNewSpotCollection;
  }

  /**
   * 
   * @return
   */
  @Override
  public SpotCollection getSpotCollection(){
    if (mySpotCollection == null){
      setBundle();
    }
    return mySpotCollection;
  }

  private String capitalizeFirst(String toBeCapitalized){
    if (toBeCapitalized.length() == 0){
      return toBeCapitalized;
    }
    return toBeCapitalized.substring(0, 1).toUpperCase() + toBeCapitalized.substring(1);
  }

  /**
   * 
   * @param myMap Map of relevant keys and what we want them to be set to
   */
  @Override
  public void update(Map<String, String> myMap) {
    myDataMap = myMap;
    setBundle();
  }
  protected abstract String getMySpotType();

}
