package ooga.logic.board.Pieces.PieceBundle.SpotCollectionStorageClasses;

import java.util.Map;
import java.util.ResourceBundle;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;

public class captureStorage extends SpotCollectionStorage {
  private static final String SPOT_COLLECTION_BASE = SpotCollection.class.getPackageName();
  private static final String CAPTURE = "capture";
  private static final String MOVEMENT = "movement";

  private SpotCollection mySpotCollection;

  public captureStorage(String myString, Map<String, String> attributeMap, ResourceBundle pieceProperties,
      ResourceBundle defaultProperties, String teamMatters) {
    super(myString, attributeMap, pieceProperties, defaultProperties);
    setCapture(myString, attributeMap, pieceProperties, defaultProperties, teamMatters);
  }

  private void setCapture(String pieceToString, Map<String, String> attributeMap, ResourceBundle pieceProperties,
      ResourceBundle defaultProperties, String teamMatters) {
    try{
      if (attributeMap.containsKey(String.format("%s%s",pieceToString,CAPTURE))){
        setMyCapture(
            (SpotCollection) Class.forName(
                String.format("%s.%s",SPOT_COLLECTION_BASE ,
                    attributeMap.get(String.format("%s%s",pieceToString,CAPTURE))
                )).getConstructor().newInstance()
        );
      }
      else{
        setMyCapture(
            (SpotCollection) Class.forName(
                String.format("%s.%s",SPOT_COLLECTION_BASE ,pieceProperties.
                    getString(String.format("%s%s",CAPTURE,teamMatters)))).getConstructor().newInstance()
        );
      }
    }
    catch (Exception e){
      setDefaultCapture(pieceToString);
    }
  }

  private void setDefaultCapture(String pieceToString) {
    try{
      setMyCapture(
          (SpotCollection) Class.forName(
              String.format("%s.%s%s",SPOT_COLLECTION_BASE,pieceToString,capitalizeFirst(MOVEMENT))
          ).getConstructor().newInstance()
      );

    }
    catch(Exception e){
      e.printStackTrace();
    }
  }

  private void setMyCapture(SpotCollection captureToSet){
    mySpotCollection = captureToSet;
  }

  public SpotCollection getSpotCollection(){
    return mySpotCollection;
  }


}
