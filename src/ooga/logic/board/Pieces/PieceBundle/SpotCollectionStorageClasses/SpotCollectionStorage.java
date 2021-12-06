package ooga.logic.board.Pieces.PieceBundle.SpotCollectionStorageClasses;

import java.util.Map;
import java.util.ResourceBundle;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;

abstract public class SpotCollectionStorage {
  private static final String CAPTURE = "capture";
  private static final String MOVEMENT = "movement";
  private SpotCollection mySpotCollection;
  private static final String SPOT_COLLECTION_BASE = SpotCollection.class.getPackageName();


  public SpotCollectionStorage(String myString, Map<String, String> attributeMap,
      ResourceBundle pieceProperties, ResourceBundle defaultProperties, String teamMatters) {
      setBundle(myString, attributeMap, pieceProperties, defaultProperties, teamMatters);
  }


  private void setBundle(String pieceToString, Map<String, String> attributeMap, ResourceBundle pieceProperties,
      ResourceBundle defaultProperties, String teamMatters) {
    try{
//      if (attributeMap.containsKey(String.format("%s%s",pieceToString,capitalizeFirst(getMySpotType())))){
//        setMySpotCollection(
//            (SpotCollection) Class.forName(
//                String.format("%s.%s",SPOT_COLLECTION_BASE ,
//                    attributeMap.get(
//                        String.format("%s%s",pieceToString,capitalizeFirst(MOVEMENT)))
//                )).getConstructor().newInstance()
//        );
//      }
//      else{
        setMySpotCollection(
            (SpotCollection) Class.forName(
                String.format("%s.%s",SPOT_COLLECTION_BASE ,pieceProperties.
                    getString(
                        String.format("%s%s",getMySpotType(),teamMatters)
                        //MOVEMENT OR CAPTURE
                    ))).getConstructor().newInstance()
        );
//      }
    }
    catch (Exception e){
      setDefaultBundle(pieceToString);
    }
  }

  protected abstract String getMySpotType();


  private void setDefaultBundle(String pieceToString) {
    try{
      setMySpotCollection(
          (SpotCollection) Class.forName(
              String.format("%s.%s%s",SPOT_COLLECTION_BASE,pieceToString,capitalizeFirst(MOVEMENT))
          ).getConstructor().newInstance()
      );

    }
    catch(Exception e){
      e.printStackTrace();
    }
  }



  private void setMySpotCollection(SpotCollection myNewSpotCollection){
    mySpotCollection = myNewSpotCollection;
  }

  public SpotCollection getSpotCollection(){
    return mySpotCollection;
  }

  protected String capitalizeFirst(String toBeCapitalized){
    return toBeCapitalized.substring(0, 1).toUpperCase() + toBeCapitalized.substring(1);
  }

}
