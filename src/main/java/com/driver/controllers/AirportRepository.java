package com.driver.controllers;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.stereotype.Repository;

import org.springframework.web.bind.annotation.RequestBody;


import java.util.*;

@Repository
public class AirportRepository {

    public HashMap<String,Airport> airportDb = new HashMap<>();

    public HashMap<Integer, Flight> flightDb = new HashMap<>();

    public HashMap<Integer,List<Integer>> flightToPassengerDb = new HashMap<>(); //flightId, ListOfPassangers


    public HashMap<Integer,Passenger> passengerDb = new HashMap<>();


    public String addAirport(@RequestBody Airport airport){

        airportDb.put(airport.getAirportName(),airport);

        return "SUCCESS";
    }

    public String getLargestAirportName()
    {

        String ans = "";
        int terminals = 0;
        for(Airport airport : airportDb.values()){

            if(airport.getNoOfTerminals()>terminals){
                ans = airport.getAirportName();
                terminals = airport.getNoOfTerminals();
            }else if(airport.getNoOfTerminals()==terminals){
                if(airport.getAirportName().compareTo(ans)<0){
                    ans = airport.getAirportName();
                }
            }
        }
        return ans;
    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity,City toCity){

        //Find the duration by finding the shortest flight that connects these 2 cities directly
        //If there is no direct flight between 2 cities return -1.

        double distance = 1000000000;

        for(Flight flight:flightDb.values()){
            if((flight.getFromCity().equals(fromCity))&&(flight.getToCity().equals(toCity))){
                distance = Math.min(distance,flight.getDuration());
            }
        }

        if(distance==1000000000){
            return -1;
        }
        return distance;

    }

    public int getNumberOfPeopleOn(Date date,String airportName)
    {
        Airport airport = airportDb.get(airportName);
        if(airport==null){
            return 0;
        }
        City city = airport.getCity();
        int count = 0;
        for(Flight flight:flightDb.values()){
            if(date.equals(flight.getFlightDate()))
                if(flight.getToCity().equals(city)||flight.getFromCity().equals(city)){

                    int flightId = flight.getFlightId();
                    count = count + flightToPassengerDb.get(flightId).size();
                }
        }
        return count;
    }
    public int calculateFlightFare(Integer flightId)
    {
        int noOfPeopleBooked = flightToPassengerDb.get(flightId).size();
        return noOfPeopleBooked*50 + 3000;
    }

    public String bookATicket(Integer flightId,Integer passengerId)
    {
        if(flightToPassengerDb.get(flightId)!=null &&(flightToPassengerDb.get(flightId).size()<flightDb.get(flightId).getMaxCapacity())){


            List<Integer> passengers =  flightToPassengerDb.get(flightId);

            if(passengers.contains(passengerId)){
                return "FAILURE";
            }

            passengers.add(passengerId);
            flightToPassengerDb.put(flightId,passengers);
            return "SUCCESS";
        }
        else if(flightToPassengerDb.get(flightId)==null)
        {
            flightToPassengerDb.put(flightId,new ArrayList<>());
            List<Integer> passengers =  flightToPassengerDb.get(flightId);

            if(passengers.contains(passengerId)){
                return "FAILURE";
            }

            passengers.add(passengerId);
            flightToPassengerDb.put(flightId,passengers);
            return "SUCCESS";

        }
        return "FAILURE";
    }

    public String cancelATicket(Integer flightId,Integer passengerId)
    {
        List<Integer> passengers = flightToPassengerDb.get(flightId);
        if(passengers == null){
            return "FAILURE";
        }

        if(passengers.contains(passengerId)){
            passengers.remove(passengerId);
            return "SUCCESS";
        }
        return "FAILURE";
    }

    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId)
    {
        int count = 0;
        for(Map.Entry<Integer,List<Integer>> entry: flightToPassengerDb.entrySet()){

            List<Integer> passengers  = entry.getValue();
            for(Integer passenger : passengers){
                if(passenger==passengerId){
                    count++;
                }
            }
        }
        return count;
    }

    public String addFlight(Flight flight)
    {
        flightDb.put(flight.getFlightId(),flight);
        return "SUCCESS";
    }

    public String getAirportNameFromFlightId(Integer flightId){


        if(flightDb.containsKey(flightId)){
            City city = flightDb.get(flightId).getFromCity();
            for(Airport airport:airportDb.values()){
                if(airport.getCity().equals(city)){
                    return airport.getAirportName();
                }
            }
        }
        return null;
    }

    public int calculateRevenueOfAFlight(Integer flightId){

        int noOfPeopleBooked = flightToPassengerDb.get(flightId).size();
        int totalFare = (25 * noOfPeopleBooked * noOfPeopleBooked) + (2975 * noOfPeopleBooked);

        return totalFare;
    }

    public String addPassenger(Passenger passenger){

        passengerDb.put(passenger.getPassengerId(),passenger);
        return "SUCCESS";
    }
}