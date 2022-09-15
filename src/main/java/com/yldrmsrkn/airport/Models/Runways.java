package com.yldrmsrkn.airport.Models;

import lombok.Data;

@Data
public class Runways {

    private String id;

    private String airport_ref;

    private String airport_ident;

    /* In this part, I did not add the runway data that I could not understand. Adding it won't cause any problems.*/
    //id,"airport_ref","airport_ident","length_ft","width_ft","surface","lighted","closed","le_ident","le_latitude_deg","le_longitude_deg","le_elevation_ft","le_heading_degT","le_displaced_threshold_ft","he_ident","he_latitude_deg","he_longitude_deg","he_elevation_ft","he_heading_degT","he_displaced_threshold_ft"

}
