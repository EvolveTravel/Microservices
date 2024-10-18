package com.backendtravelbox.trip.domain.model.commands;

public record CreateTripCommand (String origin,
                                 String destination,
                                 String date){
}