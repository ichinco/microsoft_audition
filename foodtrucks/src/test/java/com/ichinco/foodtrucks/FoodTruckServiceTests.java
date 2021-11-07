package com.ichinco.foodtrucks;

import com.ichinco.foodtrucks.model.FoodTruck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class FoodTruckServiceTests {

    private FoodTruckService service;

    @BeforeEach
    void setUp() throws DuplicateLocationException {
        List<FoodTruck> foodTrucks = new ArrayList<>();

        FoodTruck truck = new FoodTruck();
        truck.setLocationId(100);
        truck.setApplicant("applicant 1");
        truck.setBlock("a");
        foodTrucks.add(truck);

        FoodTruck truck2 = new FoodTruck();
        truck2.setLocationId(1000);
        truck2.setApplicant("applicant 2");
        truck2.setBlock("a");
        foodTrucks.add(truck2);

        FoodTruck truck3 = new FoodTruck();
        truck3.setLocationId(-1);
        truck3.setApplicant("applicant 3");
        truck3.setBlock("b");
        foodTrucks.add(truck3);

        this.service = new FoodTruckService(foodTrucks);
    }

    @Test
    void getByIdHappyPath() {
        FoodTruck foodTruck = this.service.getFoodTruckById(100);
        Assert.isTrue(foodTruck.getApplicant().equals("applicant 1"), "Wrong foodtruck returned for id");
        Assert.isTrue(foodTruck.getBlock().equals("a"), "Wrong block returned for id");
    }

    @Test
    void getByIdMissing() {
        FoodTruck truck = this.service.getFoodTruckById(1);
        Assert.isNull(truck, "Expected null truck for unrecognized id");
    }

    @Test
    void getByBlockHappyPath() {
        List<FoodTruck> foodTrucks = this.service.getFoodTrucksByBlock("a");
        Assert.isTrue(foodTrucks.size() == 2, "Expected 2 foodtrucks");
        for (FoodTruck truck : foodTrucks) {
            Assert.isTrue(truck.getLocationId() == 100 || truck.getLocationId() == 1000, "Unexpected location id when getting food trucks by block");
        }
    }

    @Test
    void getByBlockMissing() {
        List<FoodTruck> foodTrucks = this.service.getFoodTrucksByBlock("q");
        Assert.isTrue(foodTrucks.size() == 0, "Expected zero food trucks for unrecognized id");
    }

    @Test
    void addTruckHappyPath() throws DuplicateLocationException {
        FoodTruck additionalFoodTruck = new FoodTruck();
        additionalFoodTruck.setLocationId(200);
        additionalFoodTruck.setBlock("c");
        additionalFoodTruck.setApplicant("new applicant");
        this.service.addFoodTruck(additionalFoodTruck);
        this.service.getFoodTruckById(200);
        Assert.isTrue(additionalFoodTruck.getApplicant().equals("new applicant"), "Newly added food truck has wrong name");
        Assert.isTrue(additionalFoodTruck.getBlock().equals("c"), "newly added food truck has wrong block");

        List<FoodTruck> foodTrucksByBlock = this.service.getFoodTrucksByBlock("c");
        Assert.isTrue(foodTrucksByBlock.size() == 1, "Expected one foodtruck");
    }

    @Test
    void addTruckDuplicateId() {
        FoodTruck additionalFoodTruck = new FoodTruck();
        additionalFoodTruck.setLocationId(100);
        assertThrows(DuplicateLocationException.class, () -> this.service.addFoodTruck(additionalFoodTruck));
    }
}
