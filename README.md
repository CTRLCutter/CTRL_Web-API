# CTRL_Web-API

## Environment variables

Following environment variables are necessary:

- DB_URL - jdbc:mysql://URLTODB:PORT/DBNAME
- DB_USER - username of the DB account
- DB_PASSWORD - password of the DB account
- API_USER - name of the API account
- API_PASSWORD - password of the API account

## Run application

- Install dependencies ``mvn install``
- Define environment variables
- Run Main-Method in CtrlWebApiApplication class

## Run with Docker

- Clone repo on your machine
- Run ``docker build -t ctrlapi:web .`` in the repo to build the image
- Use ``docker run --name ctrlwebapi -d -p 8080:8080 -e DB_URL= -e DB_USER= -e DB_PASSWORD= -e API_USER= -e API_PASSWORD= ctrlapi:web``
with defined environment variables
- Container should start on port 8080