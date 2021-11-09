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

We would aim to test changing the language of the simulation. The design supports testing by calling
the ```changeLanguage()``` method which is designed to change the language of all of the text in the
UI. The values that should be checked will be performing a label lookup on each of the buttons to
ensure that the text has changed to the appropriate language as desired. The change itself is
determined by a refresh of the UI that makes uses of the updated JavaFX locale in order to display
the correct version of the words.

The first scenario would be a sucessful change of the language type in which we perform a lookup on
each of the test labels and are able to see that the word is now change to the appropriate
translation of the English word into the different language. Each and every one of the text labels
would need to be checked to ensure this is the case.

The second scenario would be a wholly unsucessful change of the language type, wherein either the
program throws an exception (via the format for reading a value from a resource file) or the text on
the display is not actually updated. This would also be ascertained by having to check all of the
text in the Ui and determining that it stayed in the original language. This would likely be the
result of improper resource file creation or alternating failing to appropriately change the locale
/ use the locale when refreshing the text.

The third scenario would be a partial update of the language of hte text in the display. This would
be determined by simulataneously finding at least one text label which is still in the original
language as well as at least one text label that is in the desired language that was supposed to be
changed to. This would again nbe performed by performign a lookup on the text and then comparing the
ltext present in the label and determining its language. This would likely be the consequence of
forgetting/failing to update a certain text from tis locale or otherwise not iniitalizing the text
ina a way that it was refreshed and replaced based on the JavaFX locale.

### Pieces