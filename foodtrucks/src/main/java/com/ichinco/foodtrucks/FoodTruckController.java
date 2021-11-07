package com.ichinco.foodtrucks;

import com.ichinco.foodtrucks.model.FoodTruck;
import com.ichinco.foodtrucks.model.FoodTruckListResponse;
import com.ichinco.foodtrucks.model.FoodTruckResponse;
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

    private static String VERSION = "1.0";
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
    @PostMapping("/v1.0/foodtruck")
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
    @GetMapping("/v1.0/foodtruck/{locationId}")
    public FoodTruckResponse getFoodtruckByLocationId(@PathVariable(value = "locationId") Integer locationId){
        FoodTruck foodTruck = this.foodTruckService.getFoodTruckById(locationId);

        if (foodTruck == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food Truck not found");
        }

        FoodTruckResponse response = new FoodTruckResponse(VERSION, foodTruck);

        return response;
    }

    /**
     * Searches the list of food trucks by parameter
     *
     * @param block
     * @return a list of food truck objects
     */
    @GetMapping("/v1.0/foodtrucks")
    public FoodTruckListResponse getFoodtruckByBlock(@RequestParam(value = "block") String block){
        List<FoodTruck> foodTrucksByBlock = this.foodTruckService.getFoodTrucksByBlock(block);
        FoodTruckListResponse response = new FoodTruckListResponse(VERSION, foodTrucksByBlock);
        return response;
    }
}
