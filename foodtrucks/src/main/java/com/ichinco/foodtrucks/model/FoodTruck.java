package com.ichinco.foodtrucks.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * FoodTruck is a data model object for a food truck.
 *
 * At present only two fields are required - location id and block.
 */
public class FoodTruck {

    @NotNull
    @Min(value = 1, message="Location Id must be a positive number greater than 1")
    private int locationId;

    @NotNull
    @NotBlank(message = "block is mandatory")
    private String block;

    private String applicant;
    private String facilityType;
    private String cnn;
    private String locationDescription;
    private String address;
    private String blocklot;
    private String lot;
    private String permit;
    private String status;
    private String foodItems;
    private double latitude;
    private double longitude;
    private String schedule;
    private String daysHours;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    public String getCnn() {
        return cnn;
    }

    public void setCnn(String cnn) {
        this.cnn = cnn;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBlocklot() {
        return blocklot;
    }

    public void setBlocklot(String blocklot) {
        this.blocklot = blocklot;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(String foodItems) {
        this.foodItems = foodItems;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getDaysHours() {
        return daysHours;
    }

    public void setDaysHours(String daysHours) {
        this.daysHours = daysHours;
    }
}
