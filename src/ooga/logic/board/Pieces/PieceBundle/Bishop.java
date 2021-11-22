package ooga.logic.board.Pieces.PieceBundle;

import java.util.ResourceBundle;
import ooga.logic.board.Pieces.SpotCollection.BishopMovement;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;
import ooga.logic.board.coordinate.GameCoordinate;

public class Bishop extends Piece {
  private static final String PIECE_TO_STRING = "Bishop";

  public Bishop(int xPosition, int yPosition, int team){
    setPieceProperties(PIECE_TO_STRING);
    setDefaultProperties();

    setTeam(team);
    setCoordinate(new GameCoordinate(xPosition,yPosition));
    setMovement();
    setCapture();
    setJump();
  }

  private void setJump() {
    try{
      setCanJump(Boolean.parseBoolean(getPieceProperties().getString("jump")));
    }
    catch (Exception e){
      setCanJump(Boolean.parseBoolean(getDefaultProperties().getString("jump")));
    }
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
      setMyMovement(new BishopMovement());
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
