# API Review

Choose an API for which you are responsible to discuss (one or more classes depending on their size,
complexity, or dependencies).

For the parts below, each person should listen to the other's complete ideas before expressing their
own opinion. Feel free to ask questions to clarify your understanding of the design or its
justification, but not to be critical of the design. After the person is done with their thoughts,
then you can provide constructive comments about the design: if you like it, explain why; if you do
not, explain why and provide an example of where you think it fails. Together, try to come up with a
better design, instead of just telling them what you think is the "right" way to do it.

## Part 1

> How does your API encapsulate your implementation decisions?

The API encapsulates almost all implementation decisions because as the view, the only necessary
configuration information is its size and initial visual effect decisions that are made from the
controller. The rest of the visual effects are all made within the class itself. The API methods
that controll chess board appearance is a simple method that simply takes in a single Chess board
spot, with all of the implementation hidden to the method calling it.

> What abstractions is your API built on?

The API is contingent upon the Controller abstraction it is provided in order to provide call-backs
for things such as languages and view options which are via the public API methods.

> What about your API's design is intended to be flexible?

The API's design is intended to be flexible as it will generate a visual representation of the spot
that is entered as long as there is relevant styling provided in the CSS and resource filers.
Therefore, the API can easily be used to extend the application to visually display other programs /
chess cells.

> What exceptions (error cases) might occur in your API and how are they addressed?

The main error case would be passing improper Spot inputs in to the APi in terms of an individual
chess board cell; if it contains inappropriate information then the corresponding view could not be
generated. Consequently, the API will take in a Spot and then will error-check its parameters to
ensure that they contain appropriate values and are free of invalid data.

## Part 2

> How do you justify that your API is easy to learn?

The API is easy to learn as it only require the dimensiosn to be provided as well as the Spot on the
chess board. As long as the user understands the core concepts of pixel count for screen size, as
well as appropiratley formatting their Spot as desired, they can use the APi to effectively changed
the chess board to suit their desires.

> How do you justify that API leads to readable code?

The API leads to readable code because it provides an easy reference in the interface to demonstrate
the publicly accessible methjods that other scan use to effect changes on the view and update the
chess board to their desires. This allows the class itself to be more understandable as all of the
publicly accessible methods are described and readable within a single class and opposed to being
scattered around the GameView class itself.

> How do you justify that API is hard to misuse?

The API is quite hard to misuse becaue the inputs are error-checked and as noted above, there are
relatively few posible inputs that may be provided to the API. Assuming that the user has set up
their spot as desired, the view API will simply take that spot and use it. It would be difficult to
envision creating a Spot that would cause errors as it simiply specified gthe location, colour, and
corresponding image.

> Why do you think your API design is good (also define what your measure of good is)?

The API design is good in terms of simplicity and readaability since it is quite short and provides
access to the coere functionality for the game. it could perhaps be improved by adding outwardly
accessible functionality for certain featrues like game variants or languages but reading those from
property files wsowrks as well ain terms of allowing the user to specify alternative
data/innormation that can be used in the project. 
