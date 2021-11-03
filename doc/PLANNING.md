# OOGA Plan Discussion

### Team StackOverflow

### Names

* Remy Cross
* Marcus Deans
* Tim Jang
* Carter Stonesifer
* Amr Tagel-Din

## Project goals

> what are your goals for the project?

Our goals for this project are to produce an interesting application that will provide the user a
robust experience and that appears as similar to a real-life production-grade quality game. This
will entail tailoring the UI and design very attentively, as well as ensuring that the backend is
bug-free for primary gameplay. As a team, we intend to coordinate closely and set manageable,
realistic goals that allow us to achieve the overall project that we have envisioned. We also want
to challenge ourselves with new technologies such as setting up the separate REST API and database
that will allow information to be stored. We also want to extend the player profile extension to
also incorporate the online component that will retain user information across game restarts. These
opportunities will present a chance to highlight our understanding of the design prinicples we have
covered in the course thus far and allow us to see the utility of a robust design in general. We
want to produce a thorough API that we will test to enssure its extendability. Overall, we intend to
create a robust deliverable that is able to compete with real-life similar products, as it will be a
more profound learning opportunity.

## Project Emphasis

> what parts of the project do you want to spend more or less effort on (game complexity or variety, viewer, engine, etc.)?

Good program design. Scalable back-end. Flexibility and extendability to create more games based on
the API we created.

## Project Extensions

> what extensions to the project would be most interesting/useful? Basic:

* Multiple Games at Once

> Multiple Games at Once. Allow users to play multiple, possibly different variants, of games at once while running the program.

* Preferences

> Preferences. Allow users to set preferences specific to each game played (like preferred colors, player tokens, starting configurations, etc.).

Mild:

* Player Profiles
    * We will use the controller to manage the player interactions and store information such as
      identifying features as well as gameplay metrics like score
    * This will require carefully controlling the information and keeping in mind immutability
      principles

> Player Profiles. Allow users to log in, choose an avatar to be used within the game player, view personal high scores, and save their preferences (e.g., name, password, image, age (if parental controls are implemented), and favorite variants, tokens, colors, etc). Player information should be saved between runs of the program.

Challenging:

* Save Game Data in the Web
    * We plan to use REST APIs and external database to be able to save user data and game data
      online so that you can fetch the data anywhere.

> Save Game Data in the Web. Allow users to save and load game data using an online database or web server with REST API. Note, these use the JSON data format so you likely would also.

## Project Progress

> what would mark good progress for each Sprint/week?

#### Sprint 1 (Test):

One game, fully functional (front end and backend), and tested thoroughly.

#### Sprint 2 (Basic):

Implement all 4 games implemented with good design. Complete testing of all designs and a have a
functional version of the entire project without the extensions.

#### Sprint 3 (Complete):

Refactoring, extra user functionality and extensions, REST API, external database. Fix bugs.

## Project Roles

* Remy Cross: Backend
* Marcus Deans: Frontend
* Tim Jang: Rest API
* Carter Stonesifer: Controller
* Amr Tagel-Din: Backend