Amr Tagel-Din
Project Feature:
Ability to set a square from a regular square to a black hole or a portal
Test 1:
Set a given block with nothing on it equal to a portal:
Define the coordinates of the block, use a method for "setBlockToBlackHole(x,y)";
Then, when we check Status of the block it should return whatever value we define a black hole to be"

Test 2:
Set a given block with a piece on it to a black hole:
in a try catch, define the coordinates of the block, use a method for "setBlockToBlackHole(x,y)"
this should trigger the 'catch', which we will assert to true; this should ensure we throw an 
exception if we try to set an occupied square to a black hole.

Test 3:
Set a given block outside of the range of the grid to a black hole:
in a try catch, define the coordinates of the block, use a method for "setBlocktoBlackHole(x,y)"
this should trigger the catch, with an exception we will call "OutOfBoundsException" (not the 
java one), and require us to set a valid grid coordinate to a black hole.

In order to more easily test our APIs, we discussed several key points that we should take into
account:

1. Keeping classes small (following single responsibility principle) as well as concise methods that
   allow for more specific testing of a single component of the project, reducing the scope for
   error
2. Designing APIs to take parameters that are easily constructable for testing and that return
   meaningful values that can be interpreted to elucidate success or failure
3. Throwing Exceptions as necessary especially for erroneous inputs or calculations as these will
   allow for verification that problems are diagnosed and handled by the program appropriately

## Test Scenarios

### View
