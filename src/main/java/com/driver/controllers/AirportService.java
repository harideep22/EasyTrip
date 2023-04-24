package com.driver.controllers;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AirportService {

    AirportRepository airportRepository = new AirportRepository();

    public String addAirport(Airport airport)
    {
        return airportRepository.addAirport(airport);
    }

    public String getLargestAirportName()
    {
        return airportRepository.getLargestAirportName();
    }
    public String addFlight (Flight flight)
    {
        return airportRepository.addFlight(flight);
    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City city1, City city2)
    {
        return airportRepository.getShortestDurationOfPossibleBetweenTwoCities(city1,city2);
    }

    public int getNumberOfPeopleOn(Date date, String airportName)
    {
        return airportRepository.getNumberOfPeopleOn(date,airportName);
    }

    public int calculateFlightFare(Integer flightId)
    {
        return airportRepository.calculateFlightFare(flightId);
    }

    public String bookATicket(Integer flightId,Integer passengerId)
    {
        return airportRepository.bookATicket(flightId, passengerId);
    }

    public String cancelATicket(Integer flightId,Integer passengerId)
    {
        return airportRepository.cancelATicket(flightId, passengerId);
    }

    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId)
    {
        return airportRepository.countOfBookingsDoneByPassengerAllCombined(passengerId);
    }

    public String getAirportNameFromFlightId(Integer flightId)
    {
        return airportRepository.getAirportNameFromFlightId(flightId);
    }

    public int calculateRevenueOfAFlight(Integer flightId)
    {
        return airportRepository.calculateRevenueOfAFlight(flightId);
    }

    public String addPassenger(Passenger passenger)
    {
        return airportRepository.addPassenger(passenger);
    }
}