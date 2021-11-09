# Example Games

## Variants of Chess

> Describe at least three example games from your genre that differ significantly in their functionality. These example games should help clarify the abstractions you plan to use in your design, so explain why you chose them and clearly identify their functional commonalities and differences. Note, they do not need to be the final ones you implement but certainly could be.

As described elsewhere, the focus of this application will be a robust implementation of the game of
chess. However, we will have various different examples of chess that will offer the user
significantly different gameplay while still complementing our sgtandard design since much of the
implementation will be shared between the versions.

#### Standard Chess

This would be the normal game of chess as previously describerd which acts as the baseline to which
the variants are compared. This is a standard game and follows all normal rules and functionality.

#### Black Hole

In this game mode, the primary difference is that the center of the board cannot be touched by any
unit (it will be forfeited if it does). The rest of the game continues as normally but this distorts
the map significantly. Adjustments to the size of the black hole could also be made. An interesting
detail could be whether to make the centre a black hole (pieces will be lost there) or whether it is
a wall (the player can't move pieces through it). This would lead to interesting variants as the
player would have to change strategies due to movement limitations; being able to sacrifice pieces
like pawns could affect gameplay and introduce different styles.

##### Shared:

Game board, pieces, win condition, user interface

##### Differences:

Different movement functionality (keep game board, but certain movement restricted)

#### King of the Hill

In this game mode, if the king touches the centre and isn't in check, then that player wins the
game. This is a significant variation from the normal rules because the pieces will still move as
normal but the win condition will be quite different. The rules of check will still count,
generally, but there will be exceptions as the player will still be aiming to win by getting to the
centre of the board as opposed to conventional gameplay. This was chosen because it is a significant
shift from the standard game and so offers an opportunity to show the modularity of our design,
while still re-using core components.

##### Shared:

Game board, pieces, movement functionality, user interface

##### Differences:

Different win condition

#### 3-check Chess

The objective of this game variant is either to win normally or to win by checking the opposing king
as many times as possible. In this implementation, the user could also win if they were to check the
opponent's king three times.

##### Shared:

Game board, pieces, movement functionality, user interface

##### Differences:

Different win condition
