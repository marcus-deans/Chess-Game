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
