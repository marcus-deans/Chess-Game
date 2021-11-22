package ooga.logic.board.Pieces.PieceBundle;

import java.util.ResourceBundle;
import ooga.logic.board.Pieces.SpotCollection.BishopMovement;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;
import ooga.logic.board.coordinate.GameCoordinate;

public class Bishop extends Piece {

  private ResourceBundle PieceProperties;
  private ResourceBundle DefaultProperties;

  private static final String PIECES_PACKAGE = Bishop.class.getPackageName() + ".resources.";
  private static final String DEFAULT_TO_STRING = "Default";
  private static final String PIECE_TO_STRING = "Bishop";
  //private static final String PIECE_PATH="ooga.logic.board.Pieces.PieceBundle.";


  public Bishop(int xPosition, int yPosition, int team){
    PieceProperties=ResourceBundle.getBundle(PIECES_PACKAGE+PIECE_TO_STRING);
    DefaultProperties=ResourceBundle.getBundle(PIECES_PACKAGE+DEFAULT_TO_STRING);

    setTeam(team);
    setCoordinate(new GameCoordinate(xPosition,yPosition));
    setMovement();
    setCapture();
    setJump();
  }

  private void setJump() {
    try{
      setCanJump(Boolean.parseBoolean(PieceProperties.getString("jump")));
    }
    catch (Exception e){
      setCanJump(Boolean.parseBoolean(DefaultProperties.getString("jump")));
    }
  }

  private void setMovement() {
    try{
      setMyMovement(
          (SpotCollection) Class.forName(
                  String.format("ooga.logic.board.Pieces.SpotCollection.%s", PieceProperties.
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
              String.format("ooga.logic.board.Pieces.SpotCollection.%s", PieceProperties.
                  getString("capture"))).getConstructor().newInstance()
      );
    }
    catch (Exception e){
      setMyCapture(new BishopMovement());
    }
  }


}
