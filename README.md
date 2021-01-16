<pre>
When I visit GMDB
Then I can see a list of all movies.

1. We are trying to list all movies
uri: /gmdb/movies
Method: GET
Response:
   [
  {
    "title": "Rocketeer",
    "director": "Jay Light",
    "actors": "Christopher Coakley",
    "release": "2012",
    "description": "great movie",
    "rating": "6"
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
    "title": "Rocketeer",
    "director": "Jay Light",
    "actors": "Christopher Coakley",
    "release": "2012",
    "description": "great movie",
    "rating": "6"
  }

a.
When Movie is not found throw movie doesn't exist error

3.
Given an existing movie
When I submit a 5 star rating
Then I can see it in the movie details.

Introduce Update endpoint with Http Put

uri: /gmdb/movie/{title}
Method: PUT
Request:
{
    "title": "Rocketeer",
    "rating": "6"
}
Response:
  {
    "title": "Rocketeer",
    "director": "Jay Light",
    "actors": "Christopher Coakley",
    "release": "2012",
    "description": "great movie",
    "rating": "6"
  }
Status Code: 200
</pre>