# Backlog
## Use Cases
>  Write at least six Use Cases per team member that describe specific features each person expects to complete
> — these will serve as the project's initial Backlog. Focus on features you plan to complete during the first two 
> Sprints (i.e., the project's highest priority features). Note, they can be similar to those given in the assignment 
> specification, but should include more detail specific to your project's goals. This file is not in the plan folder 
> because, ideally, it should be a living document that is updated throughout the project to add new use cases as they 
> arise.


#### Controller Use Cases
* Use Case 1: Parse Data
  * Given a certain configuration, parse through the given data
    * SIM data returned as map
    * CSV returned as dimensions in integer List
    * CSV returned configuration in 2D List

* Use Case 2: Initialize Grid
  * Given the parsed data:
    * Initialize the grid based on the [dimensions] of the game 
    * Initialize the game's [pieces] and assign them positions based on their indices.
    * Send information to the view

* Use Case 3: Click to select piece
  * Using PropertyChange Events, when clicked, gather the information of that space on the board.
  * (search conditions for King being in Check, if Check false, player can select any piece to move)
    * If contains a piece belonging to the [player], find the piece's possible moves
      * (Maybe highlight them?)


* Use Case 4: Click to move/unselect piece
  * If propertyChangeEvent click is the same tile, then unselect the information from the given piece
  * Else check the conditions of the tile selected:
    * Is there an opponent's piece? Then Capture the piece, remove it from the board
    * Is it a portal? Is it a black hole? -> handle event
    * Did player move into check? Display warning
  * Update new tile with new information, remove information from previous tile (change state)
  * Change player's turn

* Use Case 5: Capturing Pieces
  * If opponent piece is in the tile that another piece is moving to, then remove the tile's information
  * Update the tile's information with the new Piece's information

* Use Case 6: Logging Turns
  * save current position when selected
  * if next position is valid, log that too
  * using chess long algebraic notation, create a label on the side to display the move





