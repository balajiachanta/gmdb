<pre>
When I visit GMDB
Then I can see a list of all movies.

1. We are trying to list all movies
uri: /gmdb/movies
Method: GET
Response:
   [
   {
   "title": "The Avengers",
   "director": "Joss Whedon",
   "actors": "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
   "release": "2012",
   "description": "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
   "rating": null
   }
   ]
Response Success Status Code : 200
Response Empty Movies Status Code: 204
   
2. 
Given an existing movie
When I visit that title
Then I can see all the movie details.

uri: /gmdb/movie/{title}
Method: GET
Response:
      {
   "title": "The Avengers",
   "director": "Joss Whedon",
   "actors": "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
   "release": "2012",
   "description": "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
   "rating": null
   }

</pre>