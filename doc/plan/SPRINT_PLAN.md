###Amr Timeline:
By 11/15/21:
make "SquareAPI", which would allow transforming a square into a black hole, portal, etc.
make "BoardAPI" which would allow you to make changes to the board

By 11/22/21:
make "pieceLogicAPI" which lets you get and tamper with a piece logic
update "SquareAPI" to allow for generating next valid move using LogicAPI
make each "pieceLogicAPI" Implement setters for which people can affect it


By 12/5/21:
implement "WinConditionAPI's" with getters and setters for the winConditions
implement "timerAPI" to get and set timer


By end:
"ruleAPI" to generate the rules for a piece (old or new)
add some sort of "price" to pieces

### Marcus
* Responsibility for view APIs
* Sprint 1: Create populated grid (pieces) with listeners for state information
* Sprint 2: Add full UI functionality (history, piece count, views/languages)
* Sprint 3: Additional UI features (player score & profile, welcome screen, piece movement animation)

###Remy
* Responsible for backend features
* Sprint 1: Implement the logic for the Coordinate API as well as the Board and Spot APIs
* Sprint 2: Implement the functionality for using resource files to set up the default games
* Sprint 3: Implement the functionality for creating custom games


###Tim
* Responsible for backend logic
* Sprint 1: Implement the logic for the Game API and make sure I can send updated data to the front end and connect with the rest of the back end.
* Sprint 2: Implement the functionality for the other variations of the game and making the design flexible
* Sprint 3: Implement REST API and storing user data in the cloud so that it can be saved. 