package ooga.logic.board.Pieces.PieceBundle;

import ooga.logic.board.Pieces.SpotCollection.BishopMovement;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;
import ooga.logic.board.coordinate.GameCoordinate;

public class Bishop extends Piece {
  private static final String PIECE_TO_STRING = "Bishop";

  public Bishop(int xPosition, int yPosition, int team){
    super(PIECE_TO_STRING, team, new GameCoordinate(xPosition,yPosition));
    setCapture();
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

  private void setDefaultCapture(String pieceToString) {
    try{
      setMyCapture(
          (SpotCollection) Class.forName(
              String.format("%s%s",pieceToString,"Capture")
          ).getConstructor().newInstance()
      );

    }
    catch(Exception e){
      //setMyCapture();
    }
  }


}
