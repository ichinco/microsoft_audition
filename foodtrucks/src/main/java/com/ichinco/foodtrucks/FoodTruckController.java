package com.ichinco.foodtrucks;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

/**
 * FoodTruckController provides an interface for accessing information about food trucks.
 *
 */
@RestController
public class FoodTruckController {

    private FoodTruckService foodTruckService;

    public FoodTruckController() {
        this.foodTruckService = new FoodTruckService();
    }

    public FoodTruckController(FoodTruckService foodTruckService) {
        this.foodTruckService = foodTruckService;
    }

    /**
     * addFoodtruck adds a new foodtruck to our service. Since this is an
     * in-memory service, the food truck will not be persisted.
     *
     * @param truck a valid food truck object
     */
    @PostMapping("/v1/foodtruck")
    public void addFoodtruck(@Valid @RequestBody FoodTruck truck) {
        try {
            this.foodTruckService.addFoodTruck(truck);
        } catch (DuplicateLocationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate Location Id");
        }
    }

    /**
     * Gets a food truck by its id
     *
     * @param locationId id of the food truck
     * @return a foodtruck if found
     */
    @GetMapping("/v1/foodtruck/{locationId}")
    public FoodTruck getFoodtruckByLocationId(@PathVariable(value = "locationId") Integer locationId){
        FoodTruck foodTruck = this.foodTruckService.getFoodTruckById(locationId);

        if (foodTruck == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food Truck not found");
        }

        return foodTruck;
    }

    /**
     * Searches the list of food trucks by parameter
     *
     * @param block
     * @return a list of food truck objects
     */
    @GetMapping("/v1/foodtrucks")
    public List<FoodTruck> getFoodtruckByBlock(@RequestParam(value = "block") String block){
        return this.foodTruckService.getFoodTrucksByBlock(block);
    }
}
