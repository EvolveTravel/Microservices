package com.backendtravelbox.trip.interfaces.rest.resource;

public record CreateTripResource (String origin,
                                  String destination,
                                  String date){
}
