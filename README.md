# Beer App

This is a sample beer API(beer-catalog) based on Java 11 and Spring Boot 2.5.x, using under the hood Brew Dog's public
API
(https://punkapi.com/documentation/v2)

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
