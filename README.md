# Player App

A simple player application with two APIs.

## Table of Contents

- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Usage](#usage)
- [Assumptions](#assumptions)
- [Key Features](#key-features)
- [Points to Improve](#points-to-improve)
- [Acknowledgments](#acknowledgments)

## Getting Started

To run the project, Docker and Docker Compose are required. Follow the steps below:

### Prerequisites

- [Docker](https://www.docker.com/)
- [Postman](https://www.postman.com/)
- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/MGrount/PlayerApp.git
   cd demo
   
2. Run via the terminal or the IDE
    ```bash
    mvn package


3. Run Docker Compose:
    ```bash
    docker-compose up --build

### Usage

* Access the application by getting the Postman collection and environment files from the demo/postman_collection.

### Assumptions

During the development of this project, the following assumptions were made:

* Some of the fields presented in the CSV that are empty presumed to be nullable.
* The CSV file supposed to be in a size that wont crash the runners memory.
* If for some reason the parser receives a broken or non valid CSV file the application will throw an exception that will be handled.
* The SaveAll method is used to store the CSV player entity into the DB without batching(note points of improvement).
* The playerID is unique and cannot be null.


### Key Features

* Database: PostgreSQL is used for data storage.
* On boot event were used to kickstart the parsing cycle(ApplicationListener).
* Containerization: Docker is utilized to create a container for the Apache server and import player.csv.
* Parsing the player.csv file into the PostgreSQL on docker init via costume parsing service so additional info could be added down the road.
* Two Rest api GET endpoints are available, getAllPlayers and getPlayerById.
* Postman collection and environment are also available in the folder.
* Java Spring used to encapsulate redundant code.
* Unit tests are available for the controller and the parser.
* Global error handling mechanism.
* Logging mechanism.

### Points to Improve

Areas for improvement include:

* Exploring more efficient SQL solutions, such as sharding and improved indexes, to optimize querying performance.
* The use of batching and pagination in order to fetch and store large sums of data.
* Loading changed state without restarting the application
* Parsing the file once by checking if it was changed
* Metrics for measuring KPIs

This project is licensed under the [MIT] - see the LICENSE.md file for details.

### Acknowledgments

The project makes use of the following technologies:

PostgreSQL for database management.
Docker for containerization.
Java and Spring.
Maven.
Include any additional acknowledgments or references as needed.