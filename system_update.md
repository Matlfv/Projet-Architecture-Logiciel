<h1>System Update</h1><hr>
To keep the game interesting, we update the game every 15 days and add a bonus.<br>
As a bonus, we can consider adding features like the following :<br>
<ul>
<li>The radius of the radar is extended by some percentage</li>
<li>The laser shooting range is extended by some percentage</li>
<li>The player cannot be killed by a laser for some percentage of its range</li>
</ul>
<hr>

To avoid a service outage during this time, we would have to roll out updates dynamically.
We can code into the API a few resources which can enable us to simply alter the parameters for a particular player or players.
<br>
These requests would have to be authorized by us, using seperate authentication. <br>
An example of these requests could be "/api/update/radar/{playerName}/{percent_increment}".<br>
As mentioned earlier, we would have to include authentication for this.<br>
<br>
The game in progress can continue as is, and all functionality would continue to work.<br>
Players would suddenly notice an improvement in their statistics, without affecting the rest of the game.<br>
<br>
We can simply add a prefix (eg /v1/) to every request coming from an old API version. This change can be implemented almost instantly without bringing down the server.
<br><br>
