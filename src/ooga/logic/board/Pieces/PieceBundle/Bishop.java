package ooga.logic.board.Pieces.PieceBundle;

import ooga.logic.board.Pieces.SpotCollection.BishopMovement;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;
import ooga.logic.board.coordinate.GameCoordinate;

public class Bishop extends Piece {
  private static final String PIECE_TO_STRING = "Bishop";

  public Bishop(int xPosition, int yPosition, int team){
    super(PIECE_TO_STRING, team, new GameCoordinate(xPosition,yPosition));
    setMovement();
    setCapture();
  }


  private void setMovement() {
    try{
      setMyMovement(
          (SpotCollection) Class.forName(
                  String.format("ooga.logic.board.Pieces.SpotCollection.%s", getPieceProperties().
                      getString("movement"))).getConstructor().newInstance()
      );
    }
    catch (Exception e){
      setDefaultMovement();
    }
  }

  private void setDefaultMovement() {
    try{
      setMyMovement(
          (SpotCollection) Class.forName(
              String.format("%s%s",PIECE_TO_STRING,"Movement")
          ).getConstructor().newInstance()
      );

    }
    catch(Exception e){
      e.printStackTrace();
    }

  }


  private void setCapture() {
    try{
      setMyCapture(
          (SpotCollection) Class.forName(
              String.format("ooga.logic.board.Pieces.SpotCollection.%s", getPieceProperties().
                  getString("capture"))).getConstructor().newInstance()
      );
    }
    catch (Exception e){
      setMyCapture(new BishopMovement());
    }
  }


}
