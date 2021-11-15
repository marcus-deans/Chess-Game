package ooga.logic.board.Pieces.SpotCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ooga.logic.board.Pieces.SpotCollection.SpecificSpotCollectionBundle.OneTimeDiagonal;
import ooga.logic.board.coordinate.Coordinate;

public class KnightMovement extends SpotCollection {

  @Override
  public List<Coordinate> getPossibleSpots(Coordinate myCoordinate) {
    List<Coordinate> myCoords = new ArrayList<>();
    List<Integer> myIntegers = Arrays.asList(-1,1);
    for (int sign1 : myIntegers){
      for (int sign2 : myIntegers){
        myCoords.addAll(new OneTimeDiagonal().getPossibleSpots(myCoordinate,sign1 * 2,
            sign2 * 1));
        myCoords.addAll(new OneTimeDiagonal().getPossibleSpots(myCoordinate,sign1 * 1,
            sign2 * 2));
      }
    }
    return myCoords;
  }
}