The game will be played as a turn-based game.<br>
Since there may be many players, we may face difficulty with concurrency as players can move at the same time, or even occupy the same space.<br>
<br>
The state of the game can be stored in the form of the following parts(as is represented in the diagram):<br>
<ul>
<li>The position of the obstacles in the radar[list(x, y)]</li>
<li>The position of the all the players in the game[list(x, y, Direction)]</li>
<li>The current alive/dead status of all the players in the game[List(boolean)]</li>
</ul>

```puml
digraph Test {
Obstacle_List -> Game_State
Player_List -> Game_State
Dead_or_Alive_List -> Game_State
}
```

<br>
The player information like email, and possibly passwords if applicable would need to be stored securely.
<br>
We could use encryption(cryptographic cipher) to store the email ids and other data, while the passwords will not be stored in plaintext but hashed using a good hashing algorithm like SHA-256<br><br>

Since we may have several games at the same time, each game would have its own copy of the PlanetMap. Since we are using a REST webservice, we can easily assign a mapping from a user to a particular PlanetMap on the backend(stored in-memory or in a database). Thus, the only possible problem in this case would be the scaling.
<br>
Each request from the user would then be mapped to the corresponding PlanetMap.<br>
```puml
digraph Test {
Player1 -> PlanetMap1
Player2 -> PlanetMap1
Player3 -> PlanetMap1
Player4 -> PlanetMap2
Player5 -> PlanetMap2
PlanetMap1 -> DB
PlanetMap2 -> DB
}
```
<br>

If we want to implement several games per player at a time, we would have to use a Game ID to keep track of which game the player wants to make a move on.<br>
We would then also have to accept the Game ID as a parameter in the GET/POST/PATCH requests we are using to run the game.<br>
Further, it would required implementation in the database and the map from user to PlanetMap to ensure the correct moves are passed on to the correct map.<br><br>
```puml
digraph Test {
GameID1 -> Player1
GameID2 -> Player1
GameID3 -> Player1
GameID4 -> Player2
Player1 -> DB
Player2 -> DB
}
```

In case the server crashes, we would require the game states to be saved to be recovered later. Database backups would be required to restore the games.
<br> The API would become unavailable till the server is up and running again. However, the DB would ideally by robust enough to restore the game states and get running again.<br><br>
<br>
Player progress would automatically be saved as part of the game state in the database after every API request, since the state is updated every time.
Authentication would required using a framework with a username/password combination (which is currently available in Spring Boot) and secure encryption and hashing.
If the player leaves the game, his position is automatically saved, meaning his progress is stored.<br>
When this player returns, it would be restored in a single API call by searching through the game state.<br><br>
To handle an unexpectedly large load, we would require outside services like load balancers which can automatically scale to larger loads.
Using services like DigitalOcean or AWS, we can easily set the load balancers to enable scaling as and when required, without disrupting the gameplay or bringing down the server.
<br><br><br>
