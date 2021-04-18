# Minesweeper

This is a REST API implementation of
a [Minesweeper](https://en.wikipedia.org/wiki/Minesweeper_(video_game)) intended to manage multiple
games by user.

### Actual deployment

The
[application](https://dhk-minesweeper.herokuapp.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config)
is deployed in [Heroku](https://dashboard.heroku.com) and can be found in:
[https://dhk-minesweeper.herokuapp.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config](https://dhk-minesweeper.herokuapp.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config)
and can be used with credentials _user2_ & _user2_

## Installation

### Prerequisites

* **PostgreSQL:** A PostgresSQL server running with initial structure created
  * **Scrips:** Having the database server, you can run the script
    [_resources/initial_data/data-postgres.sql_](resources/initial_data/data-postgres.sql)
* **Environment Variables:** It is required to set the environment variables to run the application,
  for local environment those are not required, the variables are:
  * **DATABASE_URL:** Having the datasource connection
  * **DATABASE_USER:** Username of the database
  * **DATABASE_PASS:** Password to login to the database

### Technical Specifications

#### Architecture

The application is based on
a [hexagonal architecture](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software)) having
the entrance with the controllers, then going through using _Service_ specification and finally
going to persistence using _Data Providers_.

#### Software development approach

This project is developed based
on [DomainDrivenDesign](https://martinfowler.com/bliki/DomainDrivenDesign.html) taking the _Game_
as the domain and using some _Helpers_ to the domain logic required

#### Algorithm

The _Marix_ implementation used to update and update the game is taking by using a single array.

### Build and run

This is a Spring Boot application, using gradle as so it can be started by using:

```bash
./gradlew clean build bootRun --args='--spring.profiles.active=${profile}'
```

where **profile** can be `local` if you are running it with
[_local_](resources/application-local.properties) setup, if you are running it with _environment
variables_
then you can avoid `--args='--spring.profiles.active=${profile}'`

The available APIs are documented using [swagger](https://swagger.io/specification/) in
{host}/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

## Usage

### Create a game

To start playing it is necessary to create a game, and to do that it is necessary a `POST` request
indicating the number of _rows_, the number of _columns_ and the number of _mines_ to be set in the
new game.

`POST: {{server-address}}/games`

```json
{
  "rows": 10,
  "cols": 10,
  "mines": 20
}
```

This request will return the new created game:

```json
{
  "gameId": 1,
  "gameName": null,
  "rows": 10,
  "cols": 10,
  "mines": 20,
  "flags": 0,
  "playedBoard": [
    [
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    ],
    [
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    ],
    [
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    ],
    [
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    ],
    [
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    ],
    [
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    ],
    [
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    ],
    [
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    ],
    [
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    ],
    [
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    ]
  ],
  "ended": false,
  "lost": false,
  "durationInSeconds": 0
}
```

Where:

* **gameId:** Is the id of the game created
* **gameName:** Initial Name assigned to the game
* **rows:** The amount of rows in the game
* **cols:** The amount of columns in the game
* **mines:** The amount of mines set in the game
* **flags:** The amount of flags used in the game so far
* **playedBoard:** The current state of the game:
  * **null:** Covered cell
  * **0-8:** Number of mines in adjacent cells
  * **(-1):** Flagged cell
  * **10:** Cell uncovered and with a mine in it;
* **ended:** Flag to indicated if the game is already finished
* **lost:** Flag to indicate if the game is already lost
* **durationInSeconds:**: Time in seconds of the game duration **NOT IMPLEMENTED YET**

### Retrieving a game

To retrieve a game it is necessary to know the id of an existing game, then use a `GET` request:
`GET {{server-address}}:8080/games/1`

This request will return the game, if it does not exit will throw a `404` error:

[Answer is same as creation](#create-a-game)

### Retrieving all games of the logged user

To retrieve all the games it is necessary to use a `GET` request:
`GET {{server-address}}:8080/games`

This request will return an array if all the games:

[Answer is same as creation but as array](#create-a-game)

### Uncover a cell

The game is about to uncover cells trying to avoid mines, to do this a `PUT` request is necessary,
and the id of the game to uncover the cell, the request must send the _row_ and the _col_(column) to
uncover and the _gameId_ to be applied, and optionally an indicator if the uncover action is to flag
the cell:

`PUT: {{server-address}}/games/1`

```json
{
  "row": 9,
  "col": 9,
  "gameId": 1,
  "flag": false
}
```

### UI Client

_//TODO::_

## License

[APACHE LICENSE, VERSION 2.0](https://www.apache.org/licenses/LICENSE-2.0)
