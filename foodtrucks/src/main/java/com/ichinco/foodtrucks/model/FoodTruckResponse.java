package com.ichinco.foodtrucks.model;

public class FoodTruckResponse {

    private String version;
    private FoodTruck foodTruck;

    public FoodTruckResponse(String version, FoodTruck foodTruck) {
        this.version = version;
        this.foodTruck = foodTruck;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public FoodTruck getFoodTruck() {
        return foodTruck;
    }

    public void setFoodTruck(FoodTruck foodTruck) {
        this.foodTruck = foodTruck;
    }
}
