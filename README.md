## Overview

This service provides an API for the San Francisco food truck open
dataset. This service could scale to support other food trucks in
other cities. It supports three use cases:

* Adding a new food truck
* Retrieving a food truck based on its id
* Get all food trucks

This is a solution to the [Take Home Engineering
Challenge](https://github.com/erikschlegel/take-home-engineering-challenge).

## Usage

| Method | Path | Demo | Description |
| --- | ---- | ---- | ----------- |
| GET | `/v1.0/foodtruck/[LOCATION_ID]` | [Demo](https://foodtruckservice-foodtruckapplication.azuremicroservices.io/v1.0/foodtruck/364218) | Returns a food food truck json object for the given id |
| GET | `/v1.0/foodtrucks?block=[BLOCKID]` | [Demo](https://foodtruckservice-foodtruckapplication.azuremicroservices.io/v1.0/foodtrucks?block=0234) | Returns a list of food truck object by block |
| POST | `/v1.0/foodtruck` | curl -v -H "Content-Type: application/json" -X POST -d '{"locationId":"2","block":"def", "applicant":"Best Sushi in SF"}' https://foodtruckservice-foodtruckapplication.azuremicroservices.io/v1.0/foodtruck | Adds a new food truck. Required fields are locationId and block |

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

For this project we use a simple MVC design.

The data is stored in memory on the server in two hash maps:

* A hash map of location id to food truck object
* A hash map of block to list of location ids

Each food truck object is around 500B, so a million food trucks can
fit into .5GB of RAM. This means that a single server could be used
for even several million food trucks.

## Next Steps

I did not have time to consider the following aspects as part of this project:

1. Persistent storage for the added food trucks. As written, restarting the server will reset to the initial list.
1. Metrics and Alarms
2. Security
3. CI/CD
4. Load testing
5. Custom error messages