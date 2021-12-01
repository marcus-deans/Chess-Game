####How does your API encapsulate your implementation decisions?
The three main things that the Controller API accounts for are loading and updating information, and handling changes.
The data will initialize everything the user will need throughout the game.
Handling changes will update everything the user does throughout the game.
Anything else like changing the board size mid-game would break the rules of chess and might be unfair while playing.
Each game is set up with its rules for the course of that game, setting up the game and playing the game are separate.


####What abstractions is your API built on?
The controller uses lots of instances of classes from botth view and model. The most important instances are myGameView 
and myBoard, both of with handle their abstractions on their respective front and back ends.


####What about your API's design is intended to be flexible?
Initializing from file should account for any information that might be needed anywhere else in the code. The information
taken in should be passed around as necessary and should work however it is input, and the methods in the controller apply
the same functions no matter how the game looks or operates.

####What exceptions (error cases) might occur in your API and how are they addressed?
The controller API sets a few of the guidlines that the entire game will run on.
Exceptions may occur if necessary information is missing from the datafiles, or information is input incorrectly.



####How do you justify your API is easy to learn?
The method names are direct and describe exactly what they do.

####How do you justify your API leads to readable code?
Each method in the controller interface if defined differently will still serve a similar purpose, and however you would
want to define resetGame or undoMove would still be the same class.

####How do you justify that API is hard to misuse?
If the initializing methods were used in place of the play methods, it would be difficult to play a game of chess if each
time you selected a piece the board reset.

####Why do you think your API design is good?
API design is good because it clearly defines the basic functions you might want in your game of chess.