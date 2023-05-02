# Chess
Brief Overview: 

This is a two player chess game made in university. This was a two person project and my partner is listed in the code along with myself. 

How to Play:

To move a piece type the space of the piece you would like to move followed by the space you would like to move it to. 

All chess rules are enforced. If an illegal move is tried the game will let the player know, having them go again. 
The game ends when one player puts the other in checkmate. The game will let a player know when their king is on check, and when the king cannot escape it is deemed a checkmate. 



More Info:

Difficulties: 
●	The main Difficulty was that neither my partner nor I knew how to play chess. 
●	How to make the spaces themselves not movable. 
●	Upgrading the pawn

How I solved these issues:
●	I watched and read many tutorials on how to play chess. Through this knowledge I was able to plan out the appropriate design. I used inheritance to my advantage making a generic Piece class that the specific pieces could extend from. This made me able to use super for common traits like color, and piece value, as well as functions such as getcolor and getvalue. This also allowed me to add piece specific functions where necessary and overwrite legal moves to what was allowed for each.
●	The Empty spots on the gameboard are actually pieces themselves inheriting from the Piece class. By making all moves illegal for an Empty piece I was able to stop players from moving the board itself. 
●	Pawn promotion required a promotion function in the Pawn class. When a pawn reaches the other end of the board the player can type in the piece they want to be promoted to. The function will check to see what the player chose and create a new instance of whatever piece that may be. It will then place that piece onto the board at the correct position. If the player gives no piece preference the function automatically promotes to a queen.

