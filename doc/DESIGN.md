# OOGA Design Final
### Names
Amr Tagel-Din
Carter Stonesifer
Marcus Deans
Remy Cross
Tim Jang


## Team Roles and Responsibilities

 * Team Member #1: Amr Tagel-Din
Pieces; implementing the basis for a piece and connecting it to an API, connecting Pieces to Boolean
storage classes and SpotCollectionStorage classes to evaluate and contain data. Connecting SpotCollection
Storage Classes to SpotCollection classes to evaluate piece movement and captures, and connecting these to
Specific spot collection classes to evaluate movements in relation to a piece. Worked with Remy to connect
pieces and board.

 * Team Member #2

 * Team Member #3

 * Team Member #4

 * Team Member #5


## Design goals

#### What Features are Easy to Add

Pieces: We wanted to make it super easy to mix and match pre-existing movement schemas to old move;
for example, we wanted to make it super easy to have a Bishop have the movement or the capture behavior
of another piece. We also wanted to make it super easy to define and redefine these behaviors mid-game.
This flexibility can be seen in the addition of 'atomicCapture', which was insanely easy to add to the
existing data structure that made capturing movement behaviors extremely easy. Other things we wanted
to make easy to change was what team a piece was on, whether the team affected its behavior, whether
it can jump over other pieces, whether it can cannibalize pieces, etc.

## High-level Design

#### Core Classes


## Assumptions that Affect the Design

#### Features Affected by Assumptions


## Significant differences from Original Plan


## New Features HowTo

#### Easy to Add Features

#### Other Features not yet Done

