## Overview

This service provides an API for the San Francisco food truck open
dataset. This service could scale to support other food trucks in
other cities. It supports three use cases:

* Adding a new food truck
* Retrieving a food truck based on its id
* Get all food trucks

This is a solution to the [Take Home Engineering
Challenge](https://github.com/erikschlegel/take-home-engineering-challenge).

## Example Usage and Demo

### Get a food truck by id
`/foodtruck/[LOCATION_ID]`
[Demo](https://foodtruckservice-foodtruckapplication.azuremicroservices.io/foodtruck/364218)

### Get a list of food trucks by block
`/foodtrucks?block=[BLOCKID]`
[Demo](https://foodtruckservice-foodtruckapplication.azuremicroservices.io/foodtrucks?block=0234)

### Add a new food truck
POST `/foodtruck`
`{"locationId":1, block:"abc"}`

## Development Setup

This project requires Java 11.

After cloning this git repository, run the following commands:

    cd foodtrucks           # this takes you to the code directory
    ./gradlew clean build   # gradle installs dependencies and builds the project
    ./gradlew bootRun       # gradle creates server that listens on port 8080

A server will then be running on http://localhost:8080/.

## Deployment

Prerequisites:
* [Azure CLI](https://docs.microsoft.com/en-us/cli/azure/install-azure-cli)

Run the following commands:

    cd foodtrucks
    ./gradlew clean assemble # grade packages the project

    az login
    az spring-cloud app create -n foodtruckapplication -s foodtruckservice -g FoodTruckResourceGroup --assign-endpoint true --runtime-version=Java_11
    az spring-cloud app deploy -n foodtruckapplication -s foodtruckservice -g FoodTruckResourceGroup --artifact-path build/libs/foodtrucks-0.0.1-SNAPSHOT.jar

To see the logs:

    az spring-cloud app logs -g FoodTruckResourceGroup -s foodtruckservice -n foodtruckapplication

## Design

## Next Steps