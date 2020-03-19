<h1>Mise à jour du système</h1><hr>
Afin garder le jeu intéressant, nous mettrons à jour le jeu tous les 15 jours et ajouterons un bonus.<br>
En prime, nous pourrons envisager d'ajouter des fonctionnalités, comme celles listées ci-contre :<br>
<ul>
<li>Le rayon du radar est étendu sur un certain pourcentage</li>
<li>La plage de prise de vue au laser est étendue sur un certain pourcentage</li>
<li>Le joueur ne peut pas être tué par un laser sur un certain pourcentage de sa portée</li>
</ul>
<hr>

Pour éviter une interruption de service pendant cette période, nous devrons déployer les mises à jour de manière dynamique.
Pour cela, nous pouvons ajouter dans l'API des ressources qui pourront nous permettre de simplement modifier les paramètres d'un ou de plusieurs joueurs en particulier.
<br>
Ces demandes devraient être autorisées par nous, en utilisant une authentification distincte. <br>
Un exemple pourrait être "/api/update/radar/{playerName}/{percent_increment}". <br>
Comme mentionné précédemment, nous devons inclure l'authentification pour cela.<br>
<br>
Le jeu en cours pourra continuer tel quel et toutes les fonctionnalités continueront de fonctionner. <br>
Les joueurs remarqueraient soudain une amélioration de leurs statistiques, sans affecter le reste du jeu.<br>
<br>
Nous pouvons simplement ajouter un préfixe (par exemple / v1 /) à chaque requête provenant d'une ancienne version d'API. Cette modification peut être implémentée presque instantanément sans arrêter le serveur.
<br> <br>
