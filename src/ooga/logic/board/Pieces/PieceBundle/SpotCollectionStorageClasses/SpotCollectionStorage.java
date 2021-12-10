package ooga.logic.board.Pieces.PieceBundle.SpotCollectionStorageClasses;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.ResourceBundle;
import ooga.logic.board.Pieces.SpotCollection.NoMovement;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;


/**
 * Abstract class to generally parse through and figure out the SpotCollection for a specific aspect
 * of a piece
 * @author Amr Tagel-Din
 */
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
   * Scan all the sources of information below for the atomic nature of this given piece
   * @param pieceToString String that we want to scan the attribute map for
   * @param attributeMap map of keys and values that define the pieces outside of the default;
   *                     allow for flexible pieces
   * @param pieceProperties default properties of this particular piece
   * @param defaultProperties default properties of any piece assuming that the property is not
   *                          available in the piece properties
   * @param teamMatters either an empty variable, or the team number depending on if the team affects
   *                    the manner in which the piece moves (ex: white pawns vs black pawns)
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
  protected void movementOrNone() {
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
    setMySpotCollection(new NoMovement(getWidthHeightMax()));
  }
  }

  protected SpotCollection getSpotCollectionFromBundle(ResourceBundle pieceProperties)
      throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
    Class[] params={int.class};
    return (SpotCollection) Class.forName(
        String.format("%s.%s", SPOT_COLLECTION_BASE, pieceProperties.
            getString(
                String.format("%s%s", getMySpotType(), teamMatters)
            ))
    ).getConstructor(params).newInstance(getWidthHeightMax());
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
        )).getConstructor(params).newInstance(getWidthHeightMax());
  }

  private SpotCollection getFromType(String mySpotType)
      throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
    Class[] params={int.class};
    return (SpotCollection) Class.forName(
        String.format("%s.%s%s", SPOT_COLLECTION_BASE, pieceToString, capitalizeFirst(mySpotType))
    ).getConstructor(params).newInstance(getWidthHeightMax());
  }


  protected void setMySpotCollection(SpotCollection myNewSpotCollection){
    mySpotCollection = myNewSpotCollection;
  }

  /**
   * @return The specific SpotCollection we've found
   */
  @Override
  public SpotCollection getSpotCollection(){
    if (mySpotCollection == null){
      setBundle();
    }
    return mySpotCollection;
  }

  private String capitalizeFirst(String toBeCapitalized) {
    return (toBeCapitalized.length() == 0) ? toBeCapitalized
        : toBeCapitalized.substring(0, 1).toUpperCase() + toBeCapitalized.substring(1);
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

  protected int getWidthHeightMax(){
    return Math.max(getBoardHeight(),getBoardWidth());
  }

  protected ResourceBundle getDefaultProperties() {
    return defaultProperties;
  }


}
