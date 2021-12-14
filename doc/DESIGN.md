# OOGA Design Final

### Names

> names of all people who worked on the project

* Amr Tagel-Din
* Carter Stonesifer
* Marcus Deans
* Remy Cross
* Tim Jang

## Team Roles and Responsibilities

> each person's role in developing the project

* Team Member #1: Amr Tagel-Din Pieces; implementing the basis for a piece and connecting it to an
  API, connecting Pieces to Boolean storage classes and SpotCollectionStorage classes to evaluate
  and contain data. Connecting SpotCollection Storage Classes to SpotCollection classes to evaluate
  piece movement and captures, and connecting these to Specific spot collection classes to evaluate
  movements in relation to a piece. Worked with Remy to connect pieces and board.



* Team Member #2: Remy Cross: Board/Variations
  * I implemented API for Board and the logic for the GameBoard class and all the classes associated with them
including Spot, spotAction, Coordinate, and edgePolicy. I connected all these aspects of
the game through the GameBoard class and implemented the logic in them to create the different game variations.

Carter Stonesifer: Controller

* Team Member #3

Amr Tagel-Din: Pieces

* Team Member #4

Tim Jang: Game, Player & REST API

Building the Game and Player class. Game class connects all of the backend components like Board and Piece class and setting
various pieces to certain spots on the Board from communicating with the ChessController. Game class basically is the 
communication for the ChessController and the backend. Player class deals with all of the player information and data.
Building REST API Server and online database to store player values in the online store - this allows the players to 
save their data in the database.

* Team Member #5: Marcus Deans

View: created the view classes for the entire program encompassing all of the frontend visualization
of the chess game and any user interactivity that was seen on screen. This included languages, view
control, undo/redo/reset buttons, player profile login & profile modals, game description, cheat
code accessibility, history panel, and game loading & saving. All JavaFX code was included within
this portion. Worked with Carter in paticular to complete view-controller interactions and propagate
information from the frontend all the way to the backend and vice versa during normal game
operation.

## Design goals

> what are the project's design goals, specifically what kinds of new features did you want to make easy to add

We want to be able to implement flexibility to all pieces like being about to change how a piece can move, capture,
jump, promote, etc. We also want to be able to change Spots to different variations like black hole spot, portal spot, etc.
In addition, we want to be able to change themes in the front-end and allow the users to sign up, login, and sign out
of their account and select their profile color and save their scores using username and password that saves in an online database.
We want to be able to change between all of these different variations by labeling in the initial data sim file. 

#### What Features are Easy to Add

Pieces: We wanted to make it super easy to mix and match pre-existing movement schemas to old move;
for example, we wanted to make it super easy to have a Bishop have the movement or the capture
behavior of another piece. We also wanted to make it super easy to define and redefine these
behaviors mid-game. This flexibility can be seen in the addition of 'atomicCapture', which was
insanely easy to add to the existing data structure that made capturing movement behaviors extremely
easy. Other things we wanted to make easy to change was what team a piece was on, whether the team
affected its behavior, whether it can jump over other pieces, whether it can cannibalize pieces,
etc.

View: Wanted all of the view options to be extendible so that the user could modify the view as they
saw fit and customize especially the cosmetic features to their heart's desire. This included things
like cheat codes, languages, and view options in particular but also more complex functionality such
as adding the option for more player logins and controlling that interaction accordingly.
Customization of user highlight options, cell visuals, and piece images was also desired and was
generally implemented.

Board: We wanted to make it very easy for the user to add new variations to the GameBoard itself. Therefore,
the different variations of the GameBoard, like the spot type and the edge policy, were abstracted out and 
made into a hierarchy that can be called using reflection. In order to add a new feature to these, they just
need to be defined in the package for the spotAction or the edgePolicy.

Online Database: It is easy to add additional player information to the online database since we keep the parameters
in a separate data file. It would just require a new public method to receive the new player data. 

## High-level Design

> describe the high-level design of your project, focusing on the purpose and interaction of the core classes

At a high level, the ChessController acts as the entry point to the program. It contains references
to both the GameView (frontend) and Game (backend) and regulates and mediates interactions between
teh two, particularly of important information transfer. ChessController creates GameView, then
invokes methods to populate the frontend by creating UI subpanels. ChessController similarly reads
in the data from the provided simulation/configuration files and passes that collected information
to the backend, which processes it in order to creat a new game representation on the backend
matching the user's specifications in the file. As the game is played, user input in gameView
through various panels is propagated via the Controller through the rest of the application,
particularly to the backend. This is where things like possible moves are computed as well as which
pieces can be taken by the selected piece. Given the user's selection, the backend also receives
this information from the controller and processes it to show which pieces are now missing from the
board. The controller then retrieves this information from the backend by retrieving the board's new
representation, and updates the visual representation of the board in GameView by calling its API
with the appropriate pieces on the board.

#### Core Classes

```GameBoard.java```
```Game.java```
```ChessController.java```
```GameView.java```

## Assumptions that Affect the Design

> what assumptions or decisions were made to simplify your project's design, especially those that affected adding required features

* CSV files will be written with each spot on the board represented 
by a 3-digit code where the first digit is the piece, the second
is the team, and the third is the spot type. 
("R10" represents a Rook on team 1 with a normal spot)

* Players will recognize when their king is in check

#### Features Affected by Assumptions
* Setting up the GameBoard is impacted by the first assumption because of how the key is read in
* The win condition of the game is affected by the second assumption because the game is won when the king is captured

## Significant differences from Original Plan

> address significant differences between the original plan and the final version of the project

As can be seen in the wireframe, the completed frontend design almost completely matches the
previously intended design, with almost every component identical. The main change is that hte
variant selection option was replaced with cheat codes and a description of the specific chess game.
This reflects the broader shift to including configuration settings in the simulation file as
opposed to setting them up visually in GameView.

## New Features HowTo

> describe, in detail, how to add new features to your project, especially ones you were not able to complete by the deadline

#### Easy to Add Features

Easy to add features include adding more players that belong to the two possible teams. Other easy
extensions include additional languages and view profiles that are purely data-defined.

On the board side, some easy to add features are the spot type and the logic that goes along with each 
type of spot and the edge policy of the board and the logic that accompanies that.

#### Other Features not yet Done

The primary missing frontend features is the graveyard which was not fully implemented at a visual
levels with connections to hte backend. In future, reflection would be used in order to create the
appropriate image of the dead piece matching the backend String. We intend to add a frontend modal
which will allow for the user to visually specify all of their preferences including those currently
specified from the sim files themselves. We also want to add social features for user score counting
as well as adding additional profile functionality such as profile pictures.

On the board side, one feature I wish was implemented was implementing win conditions reflectively so 
that it would be much easier for the user to add new win conditions to the game. Right now, the program
uses if statements to check the win condition, but if reflection were used, it would make it much easier 
to change how the game is won. For example, rather than trying to capture the king, the game could be won
by having a pawn reach the other side.

