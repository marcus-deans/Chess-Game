# Backlog
## Use Cases
>  Write at least six Use Cases per team member that describe specific features each person expects to complete — these will serve as the project's initial Backlog. Focus on features you plan to complete during the first two Sprints (i.e., the project's highest priority features). Note, they can be similar to those given in the assignment specification, but should include more detail specific to your project's goals. This file is not in the plan folder because, ideally, it should be a living document that is updated throughout the project to add new use cases as they arise.

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
  * User clicks on piece on front end
  * Possible coordinates to move to generated in a list based on move method
  * List is returned in method getPossibleMoves()
* Case 3
  * Piece takes another piece in front end
  * Information relayed back to backend
  * Piece.setState(0) to make that piece empty
* Case 4
  * Pawn reaches end of grid
  * Information relayed back to backend
  * User inputs state number corresponding to piece they want
    * Queen-->5, Rook-->4, etc.
  * Piece.setState(User input)

Backend: Coordinate Class (Remy)
* Case 1
  * Getters and setters for x and y coordinates represented by i and j indices
  * Used to keep track of position of individual pieces location on the board

Backend: Game Variation Interfaces (Tim)
* Case 1
  * The current game variation is determined by the information in the data file.
  * The Properties file in the game folder has the necessary commands to take in the proper interface.
  * each interface for variation represents a slight change in rules.
  * in the properties file, it collects whatever variation interface it needs to use to make a new complete variation
* Case 2
  * The current game variation is custom. 
  * The user can pick what variations they want added to the game.
  * Using the user input, the CustomGame class takes the corresponding interfaces to match the requested variation from the user.
* Case 3
  * 