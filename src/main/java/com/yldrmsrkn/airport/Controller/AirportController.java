package com.yldrmsrkn.airport.Controller;

import com.yldrmsrkn.airport.Models.Runways;
import com.yldrmsrkn.airport.Utils.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rest")
public class AirportController {

    @Autowired
    private AirportService airportService;

    //returned Top 10 countries with highest number of airports
    @GetMapping("/countriesWithHighestAirports")
    public Object countriesWithHighestAirports() {
        return airportService.getTopTenAirports();

    }

    //types supported by ex: "Us" or "United States
    @PostMapping(value = "/{countryCode}")
    public List<Runways> getAirportsWithRunways(@PathVariable(value = "countryCode") String countryCode) {
        return airportService.checkCountry(countryCode);

    }

    //types supported countryname
    @PostMapping(value = "partial/{countryName}")
    public String getPartialCountryName(@PathVariable(value = "countryName") String countryName) {
        return airportService.getCountryPartialBonus(countryName);

    }
}
