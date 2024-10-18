package com.backendtravelbox.trip.interfaces.rest.resource;

public record TripResource (Long id,
                            String origin,
                            String destination,
                            String date){
}
