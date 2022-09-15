# CountryAirport


Working Platforms
-----------------
- Java 18 must be java 11 or hihger
- This project runs on port 8080 on tomcat
  
Usage Platforms
-----------------
- Post http://localhost:8080/rest/partial/{countryNamPart}
   When searching for partial names with this feature you want as a bonus, more than one result will be returned for countries containing the same letters.
    for ex: United States Minor Outlying Islands, United States
      -http://localhost:8080/rest/partial/United S
           ![image](https://user-images.githubusercontent.com/6370588/190499714-b42e75e3-68eb-444f-a759-87fac01da09b.png)
-Post http://localhost:8080/rest/{countryCode}
            http://localhost:8080/rest/cy
               ![image](https://user-images.githubusercontent.com/6370588/190499622-5309c90c-6e77-4b87-a9d3-8f4df6a8734e.png)
      http://localhost:8080/rest/{countryName}
            http://localhost:8080/rest/Cyprus
               ![image](https://user-images.githubusercontent.com/6370588/190499517-889e2a02-b726-4f3a-b246-0e7fb32b81a7.png)

-Get http://localhost:8080/rest/countriesWithHighestAirports
    This endpoint retun Top 10 countries with highest number of airports with country code and airport number
           ![image](https://user-images.githubusercontent.com/6370588/190499429-fb9257d5-42b6-40ed-9d7a-a76b09d7dc55.png)
