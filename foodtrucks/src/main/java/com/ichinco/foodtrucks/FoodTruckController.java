package com.ichinco.foodtrucks;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FoodTruckController {

    private FoodTruckService foodTruckService;

    public FoodTruckController() {
        this.foodTruckService = new FoodTruckService();
    }

    public FoodTruckController(FoodTruckService foodTruckService) {
        this.foodTruckService = foodTruckService;
    }

    @PostMapping("/foodtruck")
    public void addFoodtruck(@Valid @RequestBody FoodTruck truck) {
        try {
            this.foodTruckService.addFoodTruck(truck);
        } catch (DuplicateLocationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate Location Id");
        }
    }

    @GetMapping("/foodtruck/{locationId}")
    public FoodTruck getFoodtruckByLocationId(@PathVariable(value = "locationId") Integer locationId){
        FoodTruck foodTruck = this.foodTruckService.getFoodTruckById(locationId);

        if (foodTruck == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food Truck not found");
        }

        return foodTruck;
    }

    @GetMapping("/foodtrucks")
    public List<FoodTruck> getFoodtruckByBlock(@RequestParam(value = "block") String block){
        return this.foodTruckService.getFoodTrucksByBlock(block);
    }
}
