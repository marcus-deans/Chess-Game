package ooga.logic.board.Pieces.SpotCollection.FlexibleCoordinateCollection;

import java.util.ArrayList;
import java.util.List;
import ooga.logic.board.Pieces.SpotCollection.LastRankSpots;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;
import ooga.logic.board.coordinate.Coordinate;

public class PromotionSpotsCreator implements AcceptsCoordinates{
  private List<Coordinate> myCoordinates;
  private SpotCollection mySpots;

  public PromotionSpotsCreator(){

  }

  protected List<Coordinate> getMyCoordinates() {
    return myCoordinates;
  }

  public void setMyCoordinates(List<Coordinate> myCoordinates) {
    this.myCoordinates = myCoordinates;
  }


  public List<Coordinate> getPossibleSpots(Coordinate coordinate) {
    return myCoordinates;
  }

  @Override
  public void addCoordinates(List<Coordinate> newCoords) {
    myCoordinates.addAll(newCoords);
  }

  @Override
  public void removeCoordinates(List<Coordinate> removeCoords) {
    myCoordinates.removeAll(removeCoords);
  }

  @Override
  public void removeAllCoordinates() {
    myCoordinates = new ArrayList<>();
  }


}
