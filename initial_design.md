Le jeu se jouera au tour par tour. <br>
Comme il peut y avoir plusieurs joueurs, nous pouvons rencontrer des difficultés avec la concurrence car les joueurs peuvent se déplacer en même temps, ou même occuper le même espace. <br>
<br>
L'état du jeu peut être stocké sous la forme des points suivants : <br>
<ul>
<li> La position des obstacles dans le radar [liste (x, y)] </li>
<li> La position de tous les joueurs du jeu [liste (x, y, direction)] </li>
<li> Le statut actuel vivant / mort de tous les joueurs du jeu [Liste (booléen)] </li>
</ul>

```puml
test digraph {
Obstacle_List -> Game_State
Player_List -> Game_State
Dead_or_Alive_List -> Game_State
}
```

<br>
Les informations sur le joueur comme le mail et éventuellement les mots de passe, le cas échéant, devraient être stockées en toute sécurité.
<br>
Nous pourrions utiliser un chiffrement cryptographique pour stocker les mails et d'autres données, tandis que les mots de passe ne seront pas stockés en texte brut mais hachés en utilisant un bon algorithme de hachage comme SHA-256 <br> <br>

Comme nous pouvons avoir plusieurs jeux en même temps, chaque jeu aura sa propre copie de PlanetMap. Étant donné que nous utilisons un service Web REST, nous pouvons facilement attribuer un mappage d'un utilisateur à un PlanetMap particulier sur le backend (stocké en mémoire ou dans une base de données). Ainsi, le seul problème possible dans ce cas serait la mise à l'échelle.
<br>
Chaque demande de l'utilisateur serait alors mappée sur le PlanetMap correspondant. <br>
```puml
test digraph {
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

Si nous voulons implémenter plusieurs jeux par joueur à la fois, nous devons utiliser un identifiant de jeu pour garder une trace du jeu sur lequel le joueur veut avancer. <br>
Nous devrons alors également accepter l'ID de jeu comme paramètre dans les demandes GET / POST / PATCH que nous utilisons pour exécuter le jeu. <br>
De plus, cela nécessiterait une implémentation dans la base de données et la carte de l'utilisateur à PlanetMap pour garantir que les mouvements corrects sont transmis à la bonne carte. <br> <br>
```puml
test digraph {
GameID1 -> Player1
GameID2 -> Player1
GameID3 -> Player1
GameID4 -> Player2
Player1 -> DB
Player2 -> DB
}
```

En cas de panne du serveur, nous exigerions que les états du jeu soient enregistrés pour être récupérés plus tard. Des sauvegardes de base de données seraient nécessaires pour restaurer les jeux.
<br> L'API deviendrait indisponible jusqu'à ce que le serveur soit de nouveau opérationnel. Cependant, la base de données devrait idéalement être suffisamment robuste pour restaurer les états du jeu et recommencer. <br> <br>
<br>
La progression du joueur serait automatiquement enregistrée dans le cadre de l'état du jeu dans la base de données après chaque demande d'API, car l'état est mis à jour à chaque fois.
L'authentification nécessiterait l'utilisation d'un cadre avec une combinaison nom d'utilisateur / mot de passe (qui est actuellement disponible dans Spring Boot), un chiffrement et un hachage sécurisés.
Si le joueur quitte la partie, sa position est automatiquement enregistrée, ce qui signifie que sa progression est enregistrée. <br>
Lorsque ce joueur revient, il sera restauré en un seul appel d'API en recherchant dans l'état du jeu. <br> <br>
Pour gérer une charge inattendue, nous aurions besoin de services externes comme des équilibreurs de charge qui peuvent automatiquement évoluer vers des charges plus importantes.
En utilisant des services tels que DigitalOcean ou AWS, nous pouvons facilement définir les équilibreurs de charge pour permettre la mise à l'échelle au fur et à mesure des besoins, sans perturber le gameplay ou arrêter le serveur.
<br> <br> <br>
