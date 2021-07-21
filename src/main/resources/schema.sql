DROP TABLE IF EXISTS PUBLIC.beer_reviews;

CREATE TABLE PUBLIC.beer_reviews
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    beerName VARCHAR(20)  NOT NULL,
    location VARCHAR2(50) NOT NULL,
    rating   INTEGER      NOT NULL,
    comment  VARCHAR(200)
);