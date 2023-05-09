# Challenge for Java Fullstack Developer position

REST API developed with Java 11 + Spring Boot + Postgresql.

### Run local
To run the API, just run the following commands in the terminal
```bash
mvn compile
mvn exec:java -Dexec.mainClass=com.example.demo.DemoApplication 
-Dspring.profiles.active=dev
```
Possible profiles are dev and prod.
Dev uses a local postgresl db called 'challenge' with default user & password 'postgres', while prod profile uses Heroku db.

### [Documentacion](https://reba-challenge.herokuapp.com/swagger-ui/index.html)
The project has a swagger documentation.
To access it, you must run the api and hit the '/swagger-ui' endpoint.

The API is also deployed in Heroku, if you want to read the docs with the correct ui, you must write in the swagger navbar '/challenge-api.yaml'

### Deploy
There is a CI Pipeline using github actions to build the API and run tests when there is a pull request or a push to the master branch.
When this checks run correctly, the last commit in the master branch is deployed to Heroku.

https://reba-challenge.herokuapp.com

You don't need any credentials to use the API

### Performance test
[Vegeta](https://github.com/tsenart/vegeta) was used to perform stress tests to the api and monitor how it reacts.
![img_2.png](img_2.png)
![img_1.png](img_1.png)
We can see that when 500 GETS per second are requested to the endpoint /personas, 99.9% of the calls response time were below 150ms, being 172ms the maximum.

### Possible Improvements
Some improvements that could be implemented are:
- Add more unit tests to achieve a better coverage with border cases.
- When you request a POST to /personas, the entity's ID should be returned, even if it is not trivial to do (since the ID is generated after the flush and springdata with it's abstractions makes it harder), it is possible to do and brings a better experience while using the API.
- Allow better flexibility when date are given. Since internally the API is using LocalDateTime, the dates in the request body should be in same format as, for example, '2020-04-19T00:00'.
- Better error handling with DB errors, for example, when you try to POST to /personas, if the phone or the address is already in use, the API returns 500.
- Complete the API with the missing REST methods, for example, PUT /phones/{phoneId} or PUT /personas/{personaId}/phone.
