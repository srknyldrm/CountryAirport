package com.yldrmsrkn.airport.Utils;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.yldrmsrkn.airport.Models.Airports;
import com.yldrmsrkn.airport.Models.Countries;
import com.yldrmsrkn.airport.Models.Runways;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
@Slf4j
public class CSVReaderService {
    private static String csvAirportPath = "src/main/resources/airports.csv";

    private static String csvCountryFilePath = "src/main/resources/countries.csv";

    private static String csvRunwayFilePath = "src/main/resources/runways.csv";

    public List<Airports> readAirportData() {
        try {
            Reader reader = Files.newBufferedReader(Path.of(csvAirportPath));

            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Airports.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return (List<Airports>) csvToBean.parse();
        } catch (IOException | IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Countries> readCountryData() {
        try {
            Reader reader = Files.newBufferedReader(Path.of(csvCountryFilePath));

            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Countries.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return (List<Countries>) csvToBean.parse();
        } catch (IOException | IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Runways> readRunwaysData() {
        try {
            Reader reader = Files.newBufferedReader(Path.of(csvRunwayFilePath));

            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Runways.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return (List<Runways>) csvToBean.parse();
        } catch (IOException | IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }
}

