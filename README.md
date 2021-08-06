# Beer App

This is a simple beer API (beer-catalog) based on Java 11 and Spring Boot 2.5.x, 
using under the hood Brew Dog's public API (https://punkapi.com/documentation/v2)

It provides basic operations on the beer information that a user can act upon.

## How to build and run the application

### Clone the repository
```javascript
git clone https://github.com/nicholasjorge/beer-catalog.git
```
### Change directory
```javascript
cd beer-catalog/
````

## How to build and run with Docker(Docker Engine installed)

### Build docker image
```javascript
./mvnw spring-boot:build-image
```
### Run docker image
```javascript
docker run -d --rm --name beer-app -p 9090:9090 beer-catalog:0.0.1-SNAPSHOT
```

### Pull from Docker Hub
```javascript
docker pull nicholasjorge/beer-app:0.0.1-SNAPSHOT
```

### Run docker image
```javascript
docker run -d --rm --name beer-app -p 9090:9090 nicholasjorge/beer-app:0.0.1-SNAPSHOT
```

## How to build and run as jar file(JRE installed)

### Build jar file
```javascript
./mvnw clean package
```
### Run jar file
```javascript
java -jar target/beer-catalog-0.0.1-SNAPSHOT.jar
```

## How to operate

Open 
1. localhost:9090/beer-catalog/swagger-ui.html for Swagger UI in order to perform API REST calls
2. localhost:9090/beer-catalog/v3/api-docs -> JSON format or 
3. localhost:9090/beer-catalog/v3/api-docs.yaml -> yaml format for Open API specs

## Beers API

|Param |Description  |
--- | --- | 
|food|Returns all beers matching the supplied food string, this performs a fuzzy match, if you need to add spaces just add an underscore (_). |
|ibuMin|Returns all beers with IBU greater than the supplied number |
|ibuMax|Returns all beers with IBU less than the supplied number |
|page|For accessing the pages of the response|
|size|For setting the amount of beers returned in each request, by default there are 25 entries in a page|

All params are represented as strings.

Example params in Swagger UI for filtering beers
```javascript
{
    "food":"salsa", 
    "size": "10"
}
```
This will be translated into query parameters chaining:
```javascript
?food=salsa&per_page=10
```
for the remote API.


IMPORTANT! Fermentation type filtering is not implemented.
Probably I would have kept a mapping between some query params and the temperatures' interval for that type(top/medium/bottom).
This mapping can be in code or in a properties file for easier configuration.
Then based on that temperature interval I would have filtered the beers returned by the remote API and only keep those who matched the interval.

## Beers Catalog API

We have the possibility to get all the beer entries from our catalog, or filter them by id or name.

#Technical aspects

- Java 11(Latest LTS)
- Spring Boot 2.5.2(The Latest release)
- Spring Boot Webflux module for WebClient as remote api invocation HTTP client.
- Spring Data JPA with H2 in-memory db for storing beer entries.(For easier setup)
- Spring Boot Actuator for production ready observability and monitoring.(Out of the box with only a dependency)
- Spring Doc OpenAPI 3 library for Swagger UI interface and generated api docs from annotations on the controllers.
- Spring Boot DevTools and Lombok for development process and boilerplate code.
- App can be run as standalone jar as well as a Docker image.

- Missing unit, integration and contract tests for production ready app.
- Missing proper logging, the only log statements are for debugging purposes.
- I chose dynamic query params for beers API because it can be extended in code.
- I could have chosen to have dedicated field for each param and base my logic on that but from some amount of params 
it would have become hard to maintain and keep clean structure.

These APIs are meant to be used in an interface where Beers API would retrieve all the information about the beer/beers
and then the Catalog API will save some of those information(in our case only id and name from the above API) 
along with some other details for further reference.

This repository is just a PoC/demo for an application, it should not be taken as granted for production deployment.