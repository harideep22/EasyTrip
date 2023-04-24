package com.driver.controllers;


import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;

import org.springframework.web.bind.annotation.*;


import java.util.Date;


@RestController
public class AirportController {

    AirportService airportService = new AirportService();


    @PostMapping("/add_airport")
    public String addAirport(@RequestBody Airport airport){

        //Simply add airport details to your database
        //Return a String message "SUCCESS"
        return airportService.addAirport(airport);
    }

    @GetMapping("/get-largest-aiport")
    public String getLargestAirportName(){

        //Largest airport is in terms of terminals 3 terminal airport is larger than 2 terminal airport
        //Incase of a tie return the Lexicographically smallest airportName

        return airportService.getLargestAirportName();
    }

    @GetMapping("/get-shortest-time-travel-between-cities")
    public double getShortestDurationOfPossibleBetweenTwoCities(@RequestParam("fromCity") City fromCity, @RequestParam("toCity")City toCity){

        //Find the duration by finding the shortest flight that connects these 2 cities directly
        //If there is no direct flight between 2 cities return -1.
        return airportService.getShortestDurationOfPossibleBetweenTwoCities(fromCity, toCity);
    }

    @GetMapping("/get-number-of-people-on-airport-on/{date}")
    public int getNumberOfPeopleOn(@PathVariable("date") Date date,@RequestParam("airportName")String airportName){

        //Calculate the total number of people who have flights on that day on a particular airport
        //This includes both the people who have come for a flight and who have landed on an airport after their flight
        return airportService.getNumberOfPeopleOn(date,airportName);
    }

    @GetMapping("/calculate-fare")
    public int calculateFlightFare(@RequestParam("flightId")Integer flightId){

        //Calculation of flight prices is a function of number of people who have booked the flight already.
        //Price for any flight will be : 3000 + noOfPeopleWhoHaveAlreadyBooked*50
        //Suppose if 2 people have booked the flight already : the price of flight will be 3000 + 2*50 = 3100
        //This will not include the current person who is trying to book, he might also be jsut checking price

        return airportService.calculateFlightFare(flightId);
    }


    @PostMapping("/book-a-ticket")
    public String bookATicket(@RequestParam("flightId")Integer flightId,@RequestParam("passengerId")Integer passengerId){

        //If the numberOfPassengers who have booked the flight is greater than : maxCapacity, in that case :
        //return a String "FAILURE"
        //Also if the passenger has already booked a flight then also return "FAILURE".
        //else if you are able to book a ticket then return "SUCCESS"
        return airportService.bookATicket(flightId,passengerId);

    }

    @PutMapping("/cancel-a-ticket")
    public String cancelATicket(@RequestParam("flightId")Integer flightId,@RequestParam("passengerId")Integer passengerId){

        //If the passenger has not booked a ticket for that flight or the flightId is invalid or in any other failure case
        // then return a "FAILURE" message
        // Otherwise return a "SUCCESS" message
        // and also cancel the ticket that passenger had booked earlier on the given flightId
        return airportService.cancelATicket(flightId,passengerId);
    }


    @GetMapping("/get-count-of-bookings-done-by-a-passenger/{passengerId}")
    public int countOfBookingsDoneByPassengerAllCombined(@PathVariable("passengerId")Integer passengerId){

        return airportService.countOfBookingsDoneByPassengerAllCombined(passengerId);
    }

    @PostMapping("/add-flight")
    public String addFlight(@RequestBody Flight flight){

        //Return a "SUCCESS" message string after adding a flight.
        return airportService.addFlight(flight);
    }


    @GetMapping("/get-aiportName-from-flight-takeoff/{flightId}")
    public String getAirportNameFromFlightId(@PathVariable("flightId")Integer flightId){

        //We need to get the starting aiport from where the flight will be taking off
        //return null incase the flightId is invalid or you are not able to find the airportName

        return airportService.getAirportNameFromFlightId(flightId);
    }


    @GetMapping("/calculate-revenue-collected/{flightId}")
    public int calculateRevenueOfAFlight(@PathVariable("flightId")Integer flightId){

        return airportService.calculateRevenueOfAFlight(flightId);
    }


    @PostMapping("/add-passenger")
    public String addPassenger(@RequestBody Passenger passenger){

        return airportService.addPassenger(passenger);
    }


}