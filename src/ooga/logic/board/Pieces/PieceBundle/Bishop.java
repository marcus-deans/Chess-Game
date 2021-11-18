package ooga.logic.board.Pieces.PieceBundle;

import java.util.Map;
import java.util.ResourceBundle;
import ooga.logic.board.Pieces.SpotCollection.BishopMovement;
import ooga.logic.board.Pieces.SpotCollection.SpotCollection;
import ooga.logic.board.coordinate.GameCoordinate;

public class Bishop extends Piece {

  private ResourceBundle PieceProperties;
  private static final String PIECES_PACKAGE = Bishop.class.getPackageName() + ".resources.";
  private static final String PIECE_TO_STRING = "Bishop";
  private static final String PIECE_PATH="ooga.logic.board.Pieces.PieceBundle.";


  public Bishop(int xPosition, int yPosition, int team){
    PieceProperties=ResourceBundle.getBundle(PIECES_PACKAGE+PIECE_TO_STRING);

    setTeam(team);
    setCoordinate(new GameCoordinate(xPosition,yPosition));
    setMovement();
    setJump();
  }

  private void setJump() {
    try{
      setCanJump(Boolean.parseBoolean(PieceProperties.getString("jump")));
    }
    catch (Exception e){
      setCanJump(false);
    }
  }

  private void setMovement() {
    try{
      setMyMovement(
          (SpotCollection) Class.forName(
                  String.format("ooga.logic.board.Pieces.PieceBundle.resources.%s", PIECE_TO_STRING))
              .getConstructor().newInstance()
      );
    }
    catch (Exception e){
      setMyMovement(new BishopMovement());
    }
  }


}
