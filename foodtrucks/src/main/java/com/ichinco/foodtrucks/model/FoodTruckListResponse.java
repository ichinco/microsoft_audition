package com.ichinco.foodtrucks.model;

import java.util.List;

public class FoodTruckListResponse {

    private String version;
    private List<FoodTruck> foodTrucks;

    public FoodTruckListResponse(String version, List<FoodTruck> foodTrucks) {
        this.version = version;
        this.foodTrucks = foodTrucks;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<FoodTruck> getFoodTrucks() {
        return foodTrucks;
    }

    public void setFoodTrucks(List<FoodTruck> foodTrucks) {
        this.foodTrucks = foodTrucks;
    }
}
