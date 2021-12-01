1. How does your API encapsulate your implementation decisions?
   1. My API encapsulates my implementation decisions by allowing us to make changes to how the board functions and is set up without affecting the other parts of the game.
2. What abstractions is your API built on?
   1. The main abstraction that the Board API is built on is separating out the logic of the board from the rest of the game. The board essentially is built from CSV files and the spots that comprise the board are put in a list. This list of spots can then have methods called on it in the game class. This allowed us to abstract out much of the game logic like the edge policies into the board rather than the game class.
3. What about your API's design is intended to be flexible?
   1. Setting up the board is intended to be extremely flexible. It uses reflection in order to set up the pieces, types of spots that comprise the board, and the edge policy that is implemented. 
4. What exceptions (error cases) might occur in your API and how are they addressed?
   1. The errors that might occur in the API are dependent upon the CSV file. We plan on addressing these through error exceptions in the view if the format of the file or the strings within the file are incorrect.


1. How do you justify that your API is easy to learn?
   1. My API for the board setup is very easy to learn because it is made up of three main methods, setupBoard, getFullBoard, and updateBoard. These are the methods that are used in the controller to set up and keep track of the game. 
2. How do you justify that API leads to readable code?
   1. My API leads to readable code because it very clearly defines how the board is set up and how it functions in relation to the other parts of the game. It separates out a lot of the functionality of the game and makes it easy to understand.
3. How do you justify that API is hard to misuse?
   1. My API is hard to misuse because the content of it is very simple. It creates a board without knowing the structure of the data, has a single getter method for the board, and an updateBoard method that just sets the board to the list it receives.
4. Why do you think your API design is good (also define what your measure of good is)?
   1. I think my API represents good design because it follows many of the principles that we have discussed throughout the year and makes my code both more readable and flexible.