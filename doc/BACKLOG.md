Amr Tagel-Din: Use Cases
Use Case 1:
We are generating the board for a fully standard game of chess.
Steps:
1) myBoard = createBoard(CSVReader myStandardBoard, Map<String, Integer> createExtras){}
2) The CSV reader first gives the number of rows and columns for our chess board
3) using Reflection, reach each CSV cell to create the fitting type of cell (Pawn, Bishop, 
Queen, King, Knight, Rook); all other pieces are just (blanks).
4) The map is empty, since we have no extras to add.

Use Case 2:
We are generating the board for a Star Wars game with 2 black holes and 2 portals
1) MyBoard = createBoard(CSVReader myStandardBoard, Map<String, Integer> createExtras){}
2) The CSV reader first gives the number of rows and columns for our chess board
3) using Reflection, reach each CSV cell to create the fitting type of cell (Pawn, Bishop,
   Queen, King, Knight, Rook); all other pieces are just (blanks).
4) The map maps "BlackHole" to 2 and "Portal" to 2;
5) Use reflection to instantiate "initializeBlackHole(2)"
6) This will find 2 unused squares and place black holes there
7) Repeat 4-6 but with "Portal" instead of blackHole, and it maps each entry portal to an exit portal

Use Case 3:
We have a program that shifts the blackHoles every 5 rounds, and we want to shift it now
1) method called shiftBlackHoles() is run
2) first it removes all the present blackHoles; removeBlackHoles (ideally uses "remove" and reflection
 to figure out what to remove)
3) then it newly creates n (likely based off of the last number of blackholes) blackHoles and 
randomly generates their placements, assuming they don't overlap with a piece; createBlackHoles(n)

Use Case 4:
We have a program that shifts the portals every y rounds; every move we decrement y by 1;
1) if (y == 0), run shiftPortals and set y to appropriateNumber (initialY or somethingBasedOff initialY)
3) first it removes all the present Portals; removePortals (ideally uses "remove" and reflection
   to figure out what to remove)
4) then it newly creates n (likely based off of the last number of portals) portals and
   randomly generates their entrances and exits, assuming they don't overlap with a piece; createBlackHoles(n)


Use Case 5:
We have a program that is running a 'FogOfWar' like mode, where you can see the shape of the whole
board but you can only see individual squares if they are 'seen' by one of your pieces
1) After a movement by either of the sides, run recalculateVision(){} assuming we have 'FogOfWar' on.
2) run 'mySeenSet.addAll(CalculateSeenCells())' for every piece active on a given side; this returns 
every single cell we can see
3) for all those cells, mark visiblity as True using the cellApi (SetVisible(true));

Use Case 6:
We have a program that is like 4D chess where we don't have the corners intact
Steps:
1) myBoard = createBoard(CSVReader myStandardBoard, Map<String, Integer> createExtras){}
2) The CSV reader first gives the number of rows and columns for our chess board, with a -1 for the 
  'cut out cells'
3) using Reflection, reach each CSV cell to create the fitting type of cell (Pawn, Bishop,
   Queen, King, Knight, Rook); all other pieces are just (blanks) or 'null'/empty
4) The map is empty, since we have no extras to add.
5) The pieces would have their logic redefined throught the sim in another area 
(ex: pawns promote earlier), turn of play runs through an arrayList in order

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
