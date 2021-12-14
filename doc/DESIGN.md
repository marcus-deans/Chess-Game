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

Remy Cross: Board

* Team Member #2

Carter Stonesifer: Controller

* Team Member #3

Amr Tagel-Din: Pieces

* Team Member #4

Tim Jang: Game & REST API

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
#### What Features are Easy to Add
Pieces: We wanted to make it super easy to mix and match pre-existing movement schemas to old move;
for example, we wanted to make it super easy to have a Bishop have the movement or the capture
behavior of another piece. We also wanted to make it super easy to define and redefine these
behaviors mid-game. This flexibility can be seen in the addition of 'atomicCapture', which was
insanely easy to add to the existing data structure that made capturing movement behaviors extremely
easy. Other things we wanted to make easy to change was what team a piece was on, whether the team
affected its behavior, whether it can jump over other pieces, whether it can cannibalize pieces,
etc.

## High-level Design
> describe the high-level design of your project, focusing on the purpose and interaction of the core classes

#### Core Classes
```GameBoard.java```
```Game.java```
```ChessController.java```
```GameView.java```

## Assumptions that Affect the Design
> what assumptions or decisions were made to simplify your project's design, especially those that affected adding required features
#### Features Affected by Assumptions

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

#### Other Features not yet Done

The primary missing frontend features is the graveyard which was not fully implemented at a visual
levels with connections to hte backend. In future, reflection would be used in order to create the
appropriate image of the dead piece matching the backend String. We intend to add a frontend modal
which will allow for the user to visually specify all of their preferences including those currently
specified from the sim files themselves. We also want to add social features for user score counting
as well as adding additional profile functionality such as profile pictures.

