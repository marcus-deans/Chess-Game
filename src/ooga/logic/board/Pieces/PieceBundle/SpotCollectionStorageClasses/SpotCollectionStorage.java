package ooga.logic.board.Pieces.PieceBundle.SpotCollectionStorageClasses;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.ResourceBundle;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;

abstract public class SpotCollectionStorage implements SpotCollectionStorageInterface {
  private static final String CAPTURE = "capture";
  private static final String MOVEMENT = "movement";
  private SpotCollection mySpotCollection;
  private static final String SPOT_COLLECTION_BASE = SpotCollection.class.getPackageName();

  private ResourceBundle pieceProperties;
  private ResourceBundle defaultProperties;
  private String pieceToString;
  private String teamMatters;


  public SpotCollectionStorage(String pieceToString, Map<String, String> attributeMap,
      ResourceBundle pieceProperties, ResourceBundle defaultProperties, String teamMatters) {
      this.pieceProperties = pieceProperties;
      this.defaultProperties = defaultProperties;
      this.pieceToString = pieceToString;
      this.teamMatters = teamMatters;
      setBundle(attributeMap);
  }

  private void setBundle( Map<String, String> attributeMap) {
    try{
      if (attributeMap.containsKey(getMySpotType())){
        setMySpotCollection(
            getSpotCollection(attributeMap)
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

  private void getDefaultState(){
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
    return (SpotCollection) Class.forName(
        String.format("%s.%s", SPOT_COLLECTION_BASE, pieceProperties.
            getString(
                String.format("%s%s", getMySpotType(), teamMatters)
            ))
    ).getConstructor().newInstance();
  }

  private SpotCollection getSpotCollection(Map<String, String> attributeMap)
      throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
    return (SpotCollection) Class.forName(
        String.format("%s.%s",SPOT_COLLECTION_BASE ,
            attributeMap.get(getMySpotType())
        )).getConstructor().newInstance();
  }

  private SpotCollection getFromType(String mySpotType)
      throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
    return (SpotCollection) Class.forName(
        String.format("%s.%s%s", SPOT_COLLECTION_BASE, pieceToString, capitalizeFirst(mySpotType))
    ).getConstructor().newInstance();
  }


  private void setMySpotCollection(SpotCollection myNewSpotCollection){
    mySpotCollection = myNewSpotCollection;
  }

  public SpotCollection getSpotCollection(){
    return mySpotCollection;
  }

  private String capitalizeFirst(String toBeCapitalized){
    if (toBeCapitalized.length() == 0){
      return toBeCapitalized;
    }
    return toBeCapitalized.substring(0, 1).toUpperCase() + toBeCapitalized.substring(1);
  }

  @Override
  public void update(Map<String, String> myMap) {
    setBundle(myMap);
  }
  protected abstract String getMySpotType();

}
