package ooga.logic.board.Pieces.PieceBundle.SpotCollectionStorageClasses;

import java.util.Map;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;

public interface SpotCollectionStorageInterface {
  SpotCollection getSpotCollection();

  void update(Map<String,String> myMap);

}
