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




#### VIEW
* Build main visual grid for gameplay (Marcus)
* Allow movement of pieces on click and visual changes (Marcus)
* Allow user to save and load the current game (Marcus)
* Visual prompt to create profile and log in (Marcus)
* Visual effect for invalid grid selection (Marcus)
* Visual representation of **valid** movement, having selected a piece (Marcus)

Backend Use Cases: InputOutput/Resource Files (Remy)
* Case 1
  * User inputs a .sim file
  * File contains a name of one of the pre-set game modes
    * Standard, Puzzle, "Star Wars", or Atomic
  * Parser reads game mode key
  * Implements resource file pertaining to specific key
  * Sets up game in backend
* Case 2
  * User inputs a .sim file
  * File contains features that they want implemented in the game
    * Edge policies, board power-ups
  * Parser reads feature keys
  * Implements specific interfaces on backend according to desired features
  * Sets up custom game in backend
* Case 3
  * User inputs a .sim file
  * File contains a name of one of the pre-set game modes
    as well as specific desired feature keys
  * Parser reads game mode key and feature keys
  * Implements resource file pertaining to specific game mode key
  * Implements specific interfaces using feature keys
  * Sets up default game in backend with additional customization

Backend Use Cases: Pieces (Remy)
* Case 1
  * Piece Interface that contains move() method, updatePos() method and setState() method
  * Child classes for each piece represented by a state(0 represents and open space)
  * Move method is implemented for each piece based on how the piece moves in the game
  * Piece is moved on front end
  * New position is fed into backend
  * Piece location is updated
* Case 2
  * Piece is in a certain Coordinate location
  * Based on coordinate location and move() method, list of possible moves is created
* Case 3
  * User clicks on piece on front end
  * List of possible moves is returned in method getPossibleMoves() and displayed
* Case 4
  * Piece takes another piece in front end
  * Information relayed back to backend
  * Piece.setState(0) to make that piece empty
* Case 5
  * Pawn reaches end of grid
  * Information relayed back to backend
  * User inputs state number corresponding to piece they want
    * Queen-->5, Rook-->4, etc.
  * Piece.setState(User input)

Backend: Coordinate Class (Remy)
* Case 1
  * Getters and setters for x and y coordinates represented by i and j indices
  * Used to keep track of position of individual pieces location on the board

