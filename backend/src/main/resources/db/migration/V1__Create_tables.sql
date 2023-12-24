CREATE TABLE location
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(255),
    category  VARCHAR(255),
    longitude VARCHAR(255),
    latitude  VARCHAR(255)
);

CREATE TABLE city (
    id SERIAL PRIMARY KEY,
    cityName VARCHAR(255),
    longitude float,
    latitude float
)