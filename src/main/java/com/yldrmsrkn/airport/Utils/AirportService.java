package com.yldrmsrkn.airport.Utils;

import com.yldrmsrkn.airport.Models.Airports;
import com.yldrmsrkn.airport.Models.Countries;
import com.yldrmsrkn.airport.Models.Runways;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AirportService {

    @Autowired
    private CSVReaderService csvReaderService;

    public List<Runways> checkCountry(String country) {

        //chec countrycode or countryName
        try {
            if (country.length() <= 2) {
                return findAirportsRunway(country.toUpperCase());
            } else {

                return findCountryWithAirportsRunway(country);
            }
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Runways> findCountryWithAirportsRunway(String country) {
        String countryValue = getCountry(country);
        List<String> airportList = getAirports(countryValue).stream().map(x -> x.getId()).collect(Collectors.toList());
        List<Runways> runways = csvReaderService.readRunwaysData();
        List<Runways> r = new ArrayList<Runways>();

        for (int i = 0; i < airportList.size(); i++) {
            String finalI = airportList.get(i);
            List<Runways> result = runways.stream().filter(x -> Objects.equals(x.getAirport_ref(), finalI)).collect(Collectors.toList());
            r.addAll(result);
        }
        return r;
    }

    public List<Runways> findAirportsRunway(String country) {
        List<String> a = getAirports(country).stream().map(x -> x.getId()).collect(Collectors.toList());

        List<Runways> runways = csvReaderService.readRunwaysData();
        List<Runways> r = new ArrayList<Runways>();

        for (int i = 0; i < a.size(); i++) {

            String finalI = a.get(i);
            List<Runways> result = runways.stream().filter(x -> Objects.equals(x.getAirport_ref(), finalI)).collect(Collectors.toList());
            r.addAll(result);
        }
        return r;

    }

    public String getCountryPartialBonus(String country) {

        List<Countries> countries = csvReaderService.readCountryData();
        String countryName = countries.stream().filter(x -> x.getName().contains(country)).map(x -> x.getName()).collect(Collectors.joining(", "));
        return countryName;
    }

    public String getCountry(String country) {

        List<Countries> countries = csvReaderService.readCountryData();
        String countryCode = countries.stream().filter(x -> Objects.equals(x.getName(), country)).map(x -> x.getCode()).collect(Collectors.joining());
        return countryCode;
    }

    public List<Airports> getAirports(String country) {

        List<Airports> airports = csvReaderService.readAirportData();

        List<Airports> airportsForCountry = airports.
                stream().
                filter(x -> Objects.nonNull(x.getIso_country())).
                filter(x -> Objects.equals(x.getIso_country(), country)).
                collect(Collectors.toList());
        return airportsForCountry;
    }

    public Object getTopTenAirports() {

        List<Airports> airports = csvReaderService.readAirportData();


       /*
       //I leave this for you to see my way of thinking and my approach to writing methods
       HashMap<String, List<Airports>> airportsForCountry = (HashMap<String, List<Airports>>) airports
                .stream()
                .collect(Collectors.groupingBy(Airports::getIso_country));*/

        List<Airports> topAirport = airports.stream().collect(Collectors.toList());

        List<Countries> topCountry = csvReaderService.readCountryData();
        HashMap<String, Integer> listCountryCodeAirportsSize = new HashMap<>();
        for (int i = 0; i < topCountry.size(); i++) {
            //find countryCode
            String finalI = String.valueOf(topCountry.get(i).getCode());
            //findCountryAirport
            Integer result = Math.toIntExact(topAirport.stream().filter(x -> Objects.equals(x.getIso_country(), finalI)).collect(Collectors.counting()));
            listCountryCodeAirportsSize.put(finalI, result);
        }
        return sortByValue(listCountryCodeAirportsSize);
    }

    public HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        //put data sorted and limited
        Integer count = 0;
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            if (count < 10) {
                temp.put(aa.getKey(), aa.getValue());
                count++;
            }
        }
        return temp;
    }
}
