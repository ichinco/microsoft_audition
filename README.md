## Overview

This service provides an API for the San Francisco food truck open
dataset. This service could scale to support other food trucks in
other cities. It supports three use cases:

* Adding a new food truck
* Retrieving a food truck based on its id
* Get all food trucks

This is a solution to the [Take Home Engineering
Challenge](https://github.com/erikschlegel/take-home-engineering-challenge).

## Demo

### Get a food truck by id
http://localhost:8080/foodtruck/364218

### Get a list of food trucks by block
http://localhost:8080/foodtrucks?block=0234

### Add a new food truck
POST http://localhost:8080/foodtruck
`{"locationId":1, block:"abc"}`


## Development Setup

This project requires Java 11.

After cloning this git repository, run the following commands:

    cd foodtrucks           # this takes you to the code directory
    ./gradlew clean build   # gradle installs dependencies and builds the project
    ./gradlew bootRun       # gradle creates server that listens on port 8080

## Deployment

## Design

## Next Steps