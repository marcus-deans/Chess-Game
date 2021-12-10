# OOGA API review
###Partner Name: Reya Magan
###My name: Amr Tagel-Din, my netID: aht14


# API Review

Choose an API for which you are responsible to discuss (one or more classes depending on their size,
complexity, or dependencies).

API Chosen: The API's behind SpotCollection and Pieces

## Part 1

> How does your API encapsulate your implementation decisions?

At the time, the API for pieces held all the basics we wanted to be either able to be assign to the
piece, or allow the piece to return. This included move options, capture options, team, value, etc.
In other words, a Piece was a fancy collection of variables we want to be able to get and set easily;
the piece itself, in theory, should not be responsible for too much 'thought'.

> What abstractions is your API built on?

The API is built on the idea that we work with an empty board, and rely on board-related logic to be
handled by the Board API. It's also based off the idea that it'll call upon miniature API's, like the
SpotCollection to handle its logic for it.

> What about your API's design is intended to be flexible?

In theory, the piece should have a super flexible connection to a type of movement, a type of capture,
whether or not the team its on affects its properties, if it can cannibalize, etc.

> What exceptions (error cases) might occur in your API and how are they addressed?

The main error case would be if we don't pass enough information down to build a functioning piece;
in this case, we would want to have a set of default information for us to be able to use if nothing 
worth using has been passed down.

## Part 2

> How do you justify that your API is easy to learn?

The API is easy to learn as it only require the dimensiosn to be provided as well as the Spot on the
chess board. As long as the user understands the core concepts of pixel count for screen size, as
well as appropiratley formatting their Spot as desired, they can use the APi to effectively changed
the chess board to suit their desires.

> How do you justify that API leads to readable code?

Piece does a good job of keeping things encapsulated and easy to understand; for any variable that isn't
a standard one, like a "SpotCollection", we call on something else to handle the logic. By distributing
API function it's hopefully less difficult to learn a basic rule for each API rather than a large,
complicated API.

> How do you justify that API is hard to misuse?

As mentioned above, each API is seemingly geared towards one responsibility, and will rely on other
API's if needed to take care of its logic for it. So in that case, it's hard to use an API since it
can only do so much. However, if someone didn't understand how they connected together its possible
they'd misuse one, which would significantly affect the network of API's the pieces requires.

> Why do you think your API design is good (also define what your measure of good is)?

I think my API design is one that's super easy to understand and fit into any kind of 'game piece';
I also think how split up the work is is vital to making it easy to understand and hard to misuse.
