<h1>Mise à jour du système</h1><hr>
pour garder le jeu intéressant, nous mettons à jour le jeu tous les 15 jours et ajoutons un bonus.<br>
En prime, nous pouvons envisager d'ajouter des fonctionnalités comme les suivantes :<br>
<ul>
<li>Le rayon du radar est étendu sur un certain pourcentage</li>
<li>La plage de prise de vue au laser est étendue sur un certain pourcentage</li>
<li>Le joueur ne peut pas être tué par un laser sur un certain pourcentage de sa portée</li>
</ul>
<hr>

Pour éviter une interruption de service pendant cette période, nous devons déployer les mises à jour de manière dynamique.
Nous pouvons ajouter dans l'API quelques ressources qui peuvent nous permettre de simplement modifier les paramètres d'un ou de plusieurs joueurs en particulier.
<br>
Ces demandes devraient être autorisées par nous, en utilisant une authentification distincte. <br>
Un exemple de ces demandes pourrait être "/ api / update / radar / {playerName} / {percent_increment}". <br>
Comme mentionné précédemment, nous devons inclure l'authentification pour cela.<br>
<br>
Le jeu en cours peut continuer tel quel et toutes les fonctionnalités continueront de fonctionner. <br>
Les joueurs remarqueraient soudain une amélioration de leurs statistiques, sans affecter le reste du jeu.<br>
<br>
Nous pouvons simplement ajouter un préfixe (par exemple / v1 /) à chaque requête provenant d'une ancienne version d'API. Cette modification peut être implémentée presque instantanément sans arrêter le serveur.
<br> <br>
