> Describe at least two specific strategies your team discussed to make your APIs more easily testable (such as useful parameters and return values, using smaller classes or methods, throwing Exceptions, etc.). Describe at least three test scenarios each for four project features (at least one of which is negative/sad/error producing), the expected outcome, and how your design supports testing for it (such as methods you can call, specific values you can use as parameters and return values to check, specific expected UI displays or changes that could be checked, etc.). A test scenario describes the the expected results from a user's action to initiate a feature in your program and the steps that lead to that result. It is generally believed that easily testable code is better designed so this sets the team's commitment to the project's quality by addressing expectations for both "happy" and "sad" possible input/interactions.

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