# rps-game
Rock Paper Scissors game

First Part
•	Write a program to play rounds of Rock, Paper, Scissors (https://en.wikipedia.org/wiki/Rock-paper-scissors).
•	There will be 2 kinds of players, one should always choose randomly, the other should always choose rock. 
•	The program will be a web, with a single view containing the next elements:
o	"Play Round" button that will play an automatic round
o	Field showing the number of rounds played by that user so far.
o	Table showing the rounds played, with 3 columns: what 1st player chose, what second chose, and the result of the round (that could be player 1 wins, player 2 wins or draw)
o	There will be a button: "Restart game" that will set round count for that user to 0 and also clean the table.
•	Many users accessing the URL in their own browsers should be able to play the game independently, with their own counters and rounds info tables.

  Second part
•	Add a second simple view with the next information: 
o	Total rounds played
o	Total Wins for first players
o	Total Wins for second players
o	Total draws
•	These totals should consider all the rounds of all the games played by all users. (even if we clicked in "Restart button", these games should be considered as well)
•	Don't use database, just keep the information you need in memory.
