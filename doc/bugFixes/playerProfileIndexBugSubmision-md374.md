## Description

Player profile indexing does not work

## Expected Behavior

The player profile maintains the same number throughout and works properly

## Current Behavior

Currently the player profile is incremented once you lgin so that player 2 becomes player 3, even
when player 3 hasn't created a profile yet

## Steps to Reproduce

Provide detailed steps for reproducing the issue.

1. Set up a game of chess
2. Click Player 1 Login
3. Complete form successfully
4. Observe that 'Player 2 Login' is now 'Player 3 Profile'

## Failure Logs

N/A

## Hypothesis for Fixing the Bug

Hypothesis 1)
Print the player indices in the UI creator method and elucidate whether the index matches the
desired numbering scheme.

Hypothesis 2)
Print the player indices in the Game Controller and determine where the incrementing error
originates
