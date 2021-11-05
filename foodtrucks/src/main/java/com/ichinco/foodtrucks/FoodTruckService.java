package com.ichinco.foodtrucks;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * FoodTruckService provides an abstraction around a csv file provided by the city of San Francisco
 * with information about permitted food trucks. The implemenation is currently in-memory but could be
 * easily swapped out with a database or cache server implementation.
 *
 */
public class FoodTruckService {

    private Map<Integer, FoodTruck> foodTruckLocationMap;
    private Map<String, List<Integer>> foodTruckBlockMap;

    public FoodTruckService(List<FoodTruck> foodTrucks) throws DuplicateLocationException {
        foodTruckLocationMap = new HashMap<>();
        foodTruckBlockMap = new HashMap<>();

        for (FoodTruck foodTruck : foodTrucks) {
            this.addFoodTruck(foodTruck);
        }
    }

    public FoodTruckService() {
        foodTruckLocationMap = new HashMap<>();
        foodTruckBlockMap = new HashMap<>();

        try {
            CSVFormat format = CSVFormat.Builder.create(CSVFormat.DEFAULT)
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build();
            InputStream stream = new ClassPathResource("food_trucks.csv").getInputStream();
            Iterable<CSVRecord> records = CSVParser.parse(new InputStreamReader(stream), format);

            for (CSVRecord record : records) {
                FoodTruck truck = new FoodTruck();
                truck.setLocationId(Integer.parseInt(record.get("locationid")));
                truck.setApplicant(record.get("Applicant"));
                truck.setBlock(record.get("block"));
                truck.setFacilityType(record.get("FacilityType"));
                truck.setCnn(record.get("cnn"));
                truck.setLocationDescription(record.get("LocationDescription"));
                truck.setAddress(record.get("Address"));
                truck.setBlocklot(record.get("blocklot"));
                truck.setLot(record.get("lot"));
                truck.setPermit(record.get("permit"));
                truck.setStatus(record.get("Status"));
                truck.setFoodItems(record.get("FoodItems"));
                truck.setLatitude(Double.parseDouble(record.get("Latitude")));
                truck.setLongitude(Double.parseDouble(record.get("Longitude")));
                truck.setSchedule(record.get("Schedule"));
                truck.setDaysHours(record.get("dayshours"));

                this.addFoodTruck(truck);
            }
        } catch (IOException | DuplicateLocationException e) {
            throw new RuntimeException(e);
        }
    }

    public FoodTruck getFoodTruckById(Integer id) {
        return this.foodTruckLocationMap.get(id);
    }

    public List<FoodTruck> getFoodTrucksByBlock(String block) {
        List<Integer> foodTrucks = this.foodTruckBlockMap.get(block);
        Stream<Integer> foodTruckStream = Optional.ofNullable(foodTrucks).map(Collection::stream)
                                                  .orElseGet(Stream::empty);
        return foodTruckStream.map(x -> this.getFoodTruckById(x))
                              .collect(Collectors.toList());
    }

    public void addFoodTruck(FoodTruck truck) throws DuplicateLocationException {
        if (this.foodTruckLocationMap.containsKey(truck.getLocationId())) {
            throw new DuplicateLocationException();
        }

        foodTruckLocationMap.put(truck.getLocationId(), truck);
        List<Integer> trucks = foodTruckBlockMap.get(truck.getBlock());
        if (trucks == null) {
            trucks = new ArrayList<>();
            foodTruckBlockMap.put(truck.getBlock(), trucks);
        }
        trucks.add(truck.getLocationId());
    }
}
