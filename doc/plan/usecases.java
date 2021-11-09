//  Pick at least five use cases and provide example "implementations" using only methods declared in your code interfaces. Note, these must be Java code files that compile and contain extensive comments explaining how you expect your different APIs to collaborate. You can implement each Use Case either in a single class to show how the steps are connected "procedurally" or in separate classes (that implement the necessary interfaces) to show how the steps are "distributed" across objects. In either case, make it clear which how the provided implementation relates to the Use Case. To make code that compiles, you will need to create simple "mocked up" example objects that implement your interfaces so you have something concrete to create, using new, and call methods on.


/**
 * This method defines the second click on the board after the initial first click that selects the piece that is to be
 * moved.
 * It will first undo the first click if the same piece is selected again
 * Otherwise, the method will access the piece API to determine all of the possible moves the selected piece has.
 * If the new Move is invalid, nothing happens and the player can try clicking another position
 * if there is a piece in the way, the capturePiece method would remove that piece and then update the position
 * the update position method would encorporate the chance of a blackhole or portal being present before setting
 * the new positions of the piece.
 *
 * The API being used are from the Model Interfaces that have to do with how piece are allowed to move.
 * When the position is updated, that could prompt the view API to update the ChessBoard with the new piece location
 */
public void secondClick(PropertyChangeEvent evt){
        String property = evt.getPropertyName();
        if(property.equals("Click")){
            List<Integer> newPosition=(List<Integer>)evt.getNewValue();
            if(newPosition.indexOf(0)==firstClickedPosition){
                unselectFirstClick();
            }
            else if(!Piece.getPossibleMoves(newPosition)){
                doNothing();
            }
            if(!checkTile().isEmpty()){
                capturePiece();
            }
        updatePosition();
        }
}